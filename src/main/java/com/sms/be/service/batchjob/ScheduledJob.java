package com.sms.be.service.batchjob;

import com.sms.be.constant.BookingStatus;
import com.sms.be.dto.MailDto;
import com.sms.be.model.Booking;
import com.sms.be.model.Setting;
import com.sms.be.repository.BookingRepository;
import com.sms.be.repository.SettingRepository;
import com.sms.be.service.ClientService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ScheduledJob {

    private final Logger LOGGER = LoggerFactory.getLogger(ScheduledJob.class);

    //TODO add these variable to setting table
    private final long MAX_WAIT_TIME = 15L;

    private final long TIME_TO_NOTIFY = 15L;

    private final long TIME_TO_CANCEL = 5L;

    private final String ACCOUNT_SID = "AC4c1beab06610d0943ac42f8b7fb52052";

    private final String AUTH_TOKEN = "c9704caf79eaf308da0867a9dde47420";

    private final String FROM_PHONE_NUMBER = "+15615315690";

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private SettingRepository settingRepository;

    // run each 10m
    @Scheduled(initialDelay = 1000 ,fixedDelay = 1000 * 60 * TIME_TO_NOTIFY)
    public void notifyBooking() {
        LOGGER.info(" --------------- Notify to customer start --------------- ");
        List<Booking> bookingsToNotify = bookingRepository
                .findByNotifiedIsFalseAndDateAndTimeBetween(LocalDate.now(), LocalTime.now().minusMinutes(60),
                        LocalTime.now());
        bookingsToNotify.forEach(booking -> {
            booking.setNotified(true);
            sendSMS(booking);
            bookingRepository.save(booking);
            LOGGER.info("Notified to customer with booking: \n- time: {} \n- salon: {}, {}, {}", booking.getTime(),
                    booking.getSalon().getStreet(), booking.getSalon().getDistrict().getName(),
                    booking.getSalon().getProvince().getName());
        });
        LOGGER.info("Number of bookings were notified: {}", bookingsToNotify.size());
        LOGGER.info(" --------------- Notify to customer end --------------- ");
    }

    // run each 5m
    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 60 * TIME_TO_CANCEL)
    public void cancelBooking() {
        LOGGER.info(" --------------- Cancel booking start --------------- ");
        List<Booking> bookingsToCancel = bookingRepository.findByStatusAndDateLessThanEqual(BookingStatus.WAITING, LocalDate.now());
        bookingsToCancel.stream()
                .filter(booking -> ChronoUnit.MINUTES.between(booking.getTime(), LocalTime.now()) >= MAX_WAIT_TIME)
                .forEach(booking -> {
                    booking.setStatus(BookingStatus.CANCEL);
                    sendMailInform(booking, "email.cancel");
                });

        LOGGER.info(" --------------- Cancel booking end --------------- ");
    }

    private void sendMailInform(Booking booking, String templateCode) {
        MailDto mailDto = MailDto.builder().toMail(booking.getCustomer().getEmail()).templateCode(templateCode).build();
        clientService.sendMail(mailDto);
    }

    @GetMapping("send-sms")
    public void sendSMS(Booking booking) {
        String salonAddress = String
                .join(", ", booking.getSalon().getStreet(), booking.getSalon().getDistrict().getName(),
                        booking.getSalon().getProvince().getName());
        Optional<Setting> smsNotify = settingRepository.findFirstByKey("sms.notify");
        String smsBody = smsNotify.map(setting -> setting.getValue1().replace("$$time$$", booking.getTime().toString())
                .replace("$$address$$", salonAddress)).orElseGet(
                () -> "Bạn có lịch hẹn tại v-barbershop hôm nay vào lúc: " + booking.getTime().toString()
                        + ", tại địa chỉ: " + salonAddress
                        + ", vui lòng đến trước thời gian đặt 15 phút");
        //TODO remove hardcode after upgrade send SMS service
        String toNumber = "+84" + booking.getCustomer().getPhoneNumber();
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new PhoneNumber(toNumber), // to
                new PhoneNumber(FROM_PHONE_NUMBER), // from
                smsBody).create();
    }
}
