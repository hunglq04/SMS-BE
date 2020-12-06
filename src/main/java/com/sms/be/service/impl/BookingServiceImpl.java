package com.sms.be.service.impl;

import com.sms.be.constant.BookingStatus;
import com.sms.be.constant.CommonConstants;
import com.sms.be.dto.request.BookingRequest;
import com.sms.be.dto.response.BookingResponse;
import com.sms.be.exception.CustomerNotFound;
import com.sms.be.exception.EmployeeNotFound;
import com.sms.be.exception.SalonNotFoundException;
import com.sms.be.model.*;
import com.sms.be.repository.*;
import com.sms.be.service.core.BookingService;
import com.sms.be.utils.MapperUtils;
import com.sms.be.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class BookingServiceImpl implements BookingService {

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
}
