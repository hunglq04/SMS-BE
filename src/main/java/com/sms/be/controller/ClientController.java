package com.sms.be.controller;

import com.sms.be.dto.response.SalonResponse;
import com.sms.be.dto.response.StylishInfo;
import com.sms.be.dto.response.StylishResponse;
import com.sms.be.service.core.BookingService;
import com.sms.be.service.impl.BookingServiceImpl;
import com.sms.be.service.impl.EmployeeServiceImpl;
import com.sms.be.service.impl.SalonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @GetMapping("/test")
    public String test() {
        return "api client work!";
    }

    @Autowired
    SalonServiceImpl salonService;

    @Autowired
    EmployeeServiceImpl employeeService;

    @GetMapping("/get-all-salon")
    public ResponseEntity<List<SalonResponse>> getAllSalon() {
        return new ResponseEntity<>(salonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get-all-stylish")
    public ResponseEntity<List<StylishResponse>> getAllStylish() {
        return new ResponseEntity<>(employeeService.getStylishResponse(), HttpStatus.OK);
    }

}
