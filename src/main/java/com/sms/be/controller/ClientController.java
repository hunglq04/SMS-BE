package com.sms.be.controller;

import com.sms.be.dto.response.*;
import com.sms.be.model.Service;
import com.sms.be.service.core.BookingService;
import com.sms.be.service.core.ProvinceService;
import com.sms.be.service.core.ServiceService;
import com.sms.be.service.core.ProductService;
import com.sms.be.service.impl.BookingServiceImpl;
import com.sms.be.service.impl.EmployeeServiceImpl;
import com.sms.be.service.impl.SalonServiceImpl;
import com.sms.be.service.impl.ProductServiceImpl;
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
    private ProvinceService provinceService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all-salon")
    public ResponseEntity<List<SalonResponse>> getAllSalon() {
        return new ResponseEntity<>(salonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/provinces")
    public List<ProvinceResponse> getAllProvinces() {
        return provinceService.getAllProvince();
    }

    @GetMapping("/get-all-stylish")
    public ResponseEntity<List<StylishResponse>> getAllStylish(Long salonId, String date) {
        return ResponseEntity.ok(employeeService.getStylishResponse(salonId, date));
    }
    @GetMapping("/product")
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        List<ProductResponse> products = productService.getAllProduct();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/service/booking")
    public ResponseEntity<List<ServiceBookingResponse>> getServiceForBooking() {
        List<ServiceBookingResponse> services = serviceService.getServiceForBooking();
        return ResponseEntity.ok(services);
    }

}
