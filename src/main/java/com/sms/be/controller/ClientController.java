package com.sms.be.controller;

import com.sms.be.dto.response.*;
import com.sms.be.exception.ProvinceNotFoundException;
import com.sms.be.model.OrderDetail;
import com.sms.be.model.Product;
import com.sms.be.model.Service;
import com.sms.be.service.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private SalonService salonService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;
    
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/get-all-salon")
    public ResponseEntity<List<SalonResponse>> getAllSalon() {
        return new ResponseEntity<>(salonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/provinces")
    public List<ProvinceResponse> getAllProvinces() {
        return provinceService.getAllProvince();
    }

    @GetMapping("/provinces/{provinceId}/districts")
    public List<DistrictResponse> getDistrictAndWards(@Valid @PathVariable(name = "provinceId") Long provinceId) {
        return districtService.getDistrictsAndWardsByProvinceId(provinceId);
    }

    @GetMapping("/get-all-stylish")
    public ResponseEntity<List<StylishResponse>> getAllStylish(Long salonId, String date) {
        return ResponseEntity.ok(employeeService.getStylishResponse(salonId, date));
    }
    @GetMapping("/product")
    public ResponseEntity<List<ProductResponse>> getAllProduct(String name) {
        List<ProductResponse> products = productService.getAllProduct(name);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/product/detail")
    public ResponseEntity<Optional<Product>> getProduct(Long id) {
         Optional<Product> products = productService.getProduct(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/service/booking")
    public ResponseEntity<List<ServiceBookingResponse>> getServiceForBooking() {
        List<ServiceBookingResponse> services = serviceService.getServiceForBooking();
        return ResponseEntity.ok(services);
    }
    @GetMapping("/service/booking/detail")
    public ResponseEntity<Optional<Service>> getService(Long id) {
        Optional<Service> services = serviceService.getService(id);
        return ResponseEntity.ok(services);
    }

}
