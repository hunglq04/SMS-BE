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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
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
        Account requester = SecurityUtils.getCurrentAccount();
        Customer customer = customerRepository.findByAccount(requester)
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

    @Override
    public List<BookingResponse> getBookingHistoryByCustomer() {
        Account requester = SecurityUtils.getCurrentAccount();
        Customer customer = customerRepository.findByAccount(requester)
                .orElseThrow(() -> new CustomerNotFound("No customer found"));
        return bookingRepository.findBookingHistoryByCustomer(customer).stream()
                .map(MapperUtils::bookingToBookingResponse)
                .collect(Collectors.toList());
    }
}
