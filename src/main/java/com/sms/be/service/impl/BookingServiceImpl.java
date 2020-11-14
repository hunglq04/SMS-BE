package com.sms.be.service.impl;

import com.sms.be.constant.BookingStatus;
import com.sms.be.constant.CommonConstants;
import com.sms.be.dto.request.BookingRequest;
import com.sms.be.dto.response.StylishInfo;
import com.sms.be.exception.CustomerNotFound;
import com.sms.be.exception.EmployeeNotFound;
import com.sms.be.exception.SalonNotFoundException;
import com.sms.be.model.Booking;
import com.sms.be.model.Customer;
import com.sms.be.model.Employee;
import com.sms.be.model.Salon;
import com.sms.be.repository.*;
import com.sms.be.service.core.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.spi.service.contexts.SecurityContext;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
        SecurityContextHolder.getContext();
        Customer customer = customerRepository.findById(bookingRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFound("No customer found"));
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
                .build();
        bookingRepository.save(booking);
    }
}
