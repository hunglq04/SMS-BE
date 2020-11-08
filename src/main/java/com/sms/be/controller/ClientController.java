package com.sms.be.controller;

import com.sms.be.dto.response.SalonResponse;
import com.sms.be.dto.response.ServiceBookingResponse;
import com.sms.be.dto.response.StylishInfo;
import com.sms.be.dto.response.StylishResponse;
import com.sms.be.model.Service;
import com.sms.be.service.core.BookingService;
import com.sms.be.service.core.ServiceService;
import com.sms.be.service.impl.BookingServiceImpl;
import com.sms.be.service.impl.EmployeeServiceImpl;
import com.sms.be.service.impl.SalonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private SalonServiceImpl salonService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/get-all-salon")
    public ResponseEntity<List<SalonResponse>> getAllSalon() {
        return new ResponseEntity<>(salonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get-all-stylish")
    public ResponseEntity<List<StylishResponse>> getAllStylish() {
        return new ResponseEntity<>(employeeService.getStylishResponse(), HttpStatus.OK);
    }

    @GetMapping("/service/booking")
    public ResponseEntity<List<ServiceBookingResponse>> getServiceForBooking() {
        List<ServiceBookingResponse> services = serviceService.getServiceForBooking();
        return ResponseEntity.ok(services);
    }

}
