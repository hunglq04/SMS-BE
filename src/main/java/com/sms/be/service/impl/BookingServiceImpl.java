package com.sms.be.service.impl;

import com.sms.be.constant.BookingStatus;
import com.sms.be.constant.CommonConstants;
import com.sms.be.dto.RatingImageDto;
import com.sms.be.dto.request.BookingRequest;
import com.sms.be.dto.response.BillResponse;
import com.sms.be.dto.response.BookingResponse;
import com.sms.be.exception.BookingNotFoundException;
import com.sms.be.exception.CustomerNotFound;
import com.sms.be.exception.EmployeeNotFound;
import com.sms.be.exception.SalonNotFoundException;
import com.sms.be.model.*;
import com.sms.be.repository.*;
import com.sms.be.service.core.BookingService;
import com.sms.be.utils.HMACUtil;
import com.sms.be.utils.MapperUtils;
import com.sms.be.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class BookingServiceImpl implements BookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public void bookServices(BookingRequest bookingRequest) {
        Customer customer = null;
        if (StringUtils.isBlank(bookingRequest.getWalkInGuest())) {
            Account requester = SecurityUtils.getCurrentAccount();
            customer = customerRepository.findByAccount(requester)
                    .orElseThrow(() -> new CustomerNotFound("No customer found"));
        }
        Employee stylist = employeeRepository.findEmployeeByIdAndRole(
                bookingRequest.getStylistId(), CommonConstants.ROLE_STYLIST)
                .orElseThrow(() -> new EmployeeNotFound("No stylist found"));
        List<com.sms.be.model.Service> services = serviceRepository
                .findAllByIdIn(bookingRequest.getServiceIds());
        Salon salon = salonRepository.findById(bookingRequest.getSalonId())
                .orElseThrow(SalonNotFoundException::new);
        List<Long> bookings = bookingRepository.findBookingHistoryByCustomer(customer).stream()
                .filter(booking1 -> BookingStatus.WAITING.equals(booking1.getStatus()))
                .map(Booking::getId)
                .collect(Collectors.toList());
        if (!bookings.isEmpty()) {
            bookingRepository.deleteAllByIdIn(bookings);
        }
        Booking booking = Booking.builder()
                .date(LocalDate.parse(bookingRequest.getDate()))
                .time(LocalTime.parse(bookingRequest.getTime()))
                .customer(customer)
                .stylist(stylist)
                .salon(salon)
                .services(services)
                .status(BookingStatus.WAITING)
                .walkInGuest(bookingRequest.getWalkInGuest())
                .build();
        bookingRepository.save(booking);
    }

    @Override
    public List<BookingResponse> getBookingHistoryByCustomer() {

        Account requester = SecurityUtils.getCurrentAccount();
        Customer customer = customerRepository.findByAccount(requester)
                .orElseThrow(() -> new CustomerNotFound("No customer found"));
        return bookingRepository.findBookingHistoryByCustomer(customer).stream()
                .map(MapperUtils::bookingToBookingResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookingResponse> getBookingPageByDateAndSalon(
            int pageSize, int pageOffset, String fromDate, Long salonId) {
        Employee requester = employeeRepository.findByAccount(SecurityUtils.getCurrentAccount())
                .orElseThrow(() -> new EmployeeNotFound("No employee found"));
        return bookingRepository.getBookingPageFromDateBySalon(pageSize, pageOffset, fromDate, salonId, requester)
                .map(MapperUtils::bookingToBookingResponse);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public BillResponse invoice(Long bookingId, boolean withZP) {
        Account requester = SecurityUtils.getCurrentAccount();
        Employee cashier = employeeRepository.findByAccount(requester)
                .orElseThrow(() -> new EmployeeNotFound("No Cashier found"));
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(BookingNotFoundException::new);
        if (!withZP) {
            booking.setStatus(BookingStatus.DONE);
            bookingRepository.save(booking);
        }
        Bill bill = billRepository.findByBooking(booking).orElse(
                Bill.builder()
                .booking(booking)
                .cashier(cashier)
                .customer(booking.getCustomer())
                .dateTime(LocalDateTime.now())
                .total(booking.getTotalPrice())
                .build());
        billRepository.saveAndFlush(bill);
        String zaloPayToken = "";
        try {
            zaloPayToken = createZaloPayOrder(bill);
        } catch (Exception e) {
            LOGGER.error("Create ZaloPay fail", e);
        }
        return BillResponse.builder().billId(bill.getId()).zpToken(zaloPayToken).build();
    }

    @Override
    public BookingResponse startProgress(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(BookingNotFoundException::new);
        booking.setStatus(BookingStatus.IN_PROGRESS);
        return MapperUtils.bookingToBookingResponse(booking);
    }

    @Override
    public void finishProgress(Long bookingId, RatingImageDto images) {
        Account account = SecurityUtils.getCurrentAccount();
        Employee stylist = employeeRepository.findByAccount(account).orElseThrow(EmployeeNotFound::new);
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(BookingNotFoundException::new);
        Rating rating = booking.getImages() != null ? booking.getImages() : new Rating();
        rating.setImage1(images.getImage1());
        rating.setImage2(images.getImage2());
        rating.setImage3(images.getImage3());
        rating.setImage4(images.getImage4());
        rating.setBooking(booking);
        rating.setCreatedBy(stylist);
        ratingRepository.save(rating);
    }

    public static String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    private String createZaloPayOrder(Bill bill) throws Exception {
        final Map<String, String> config = new HashMap<String, String>(){{
            put("appid", "554");
            put("key1", "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn");
            put("key2", "uUfsWgfLkRLzq6W2uNXTCxrfxs51auny");
            put("endpoint", "https://sandbox.zalopay.com.vn/v001/tpe/createorder");
        }};

        final Map embeddata = new HashMap(){{
            put("merchantinfo", "embeddata123");
        }};

        final Map[] item = bill.getBooking().getServices().stream().map(service -> {
            Map serviceItem = new HashMap();
            serviceItem.put("itemid", service.getId());
            serviceItem.put("itemname", service.getName());
            serviceItem.put("itemprice", service.getPrice());
            serviceItem.put("itemquantity", 1);
            return serviceItem;
        }).toArray(Map[]::new);

        Map<String, Object> order = new HashMap<String, Object>(){{
            put("appid", config.get("appid"));
            put("apptransid", getCurrentTimeString("yyMMdd") +"_V-BARBERSHOP-BILL-"+ bill.getId()); // mã giao dich có định dạng yyMMdd_xxxx
            put("apptime", System.currentTimeMillis()); // miliseconds
            put("appuser", "demo");
            put("amount", bill.getTotal());
            put("description", "V-barbershop ZaloPay Demo");
            put("bankcode", "zalopayapp");
            put("item", new JSONObject(item).toString());
            put("embeddata", new JSONObject(embeddata).toString());
        }};

        // appid +”|”+ apptransid +”|”+ appuser +”|”+ amount +"|" + apptime +”|”+ embeddata +"|" +item
        String data = order.get("appid") +"|"+ order.get("apptransid") +"|"+ order.get("appuser") +"|"+ order.get("amount")
                +"|"+ order.get("apptime") +"|"+ order.get("embeddata") +"|"+ order.get("item");
        order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(config.get("endpoint"));

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : order.entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }

        // Content-Type: application/x-www-form-urlencoded
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        JSONObject result = new JSONObject(resultJsonStr.toString());
        for (String key : result.keySet()) {
            LOGGER.info("{} = {}\n", key, result.get(key));
        }
        return result.get("zptranstoken").toString();
    }

}
