package com.sms.be.controller;

import com.sms.be.dto.ManagerInfoDto;
import com.sms.be.dto.request.ProductRequest;
import com.sms.be.dto.request.SalonRequest;
import com.sms.be.dto.request.ServiceRequest;
import com.sms.be.dto.response.*;
import com.sms.be.model.Product;
import com.sms.be.service.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/internal")
public class InternalController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SalonService salonService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @GetMapping("/employee/managers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ManagerInfoDto>> getAllManagers() {
        List<ManagerInfoDto> response = employeeService.getAllManagerInfos();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/salon")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> addSalon(@Valid @RequestBody SalonRequest request) {
        salonService.addNewSalon(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/salon")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<SalonInternalResponse>> getSalonPage(int pageOffset, int pageSize) {
        return ResponseEntity.ok(salonService.getSalonPage(pageOffset,
                pageSize));
    }

    @GetMapping("/product")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<ProductResponse>> getProductPage(int pageOffset, int pageSize) {
        return ResponseEntity.ok(productService.getProductPage(pageOffset,
                pageSize));
    }

    @PostMapping("/product")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> addProduct(@Valid @RequestBody ProductRequest request) {
        productService.addNewProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/product/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable(name = "id") Long id) {
        productService.updateProduct(productRequest, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/producttype")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProductTypeReponse> getAllProductType() {
        return productTypeService.getAllProductType();
    }

    @PostMapping("/service")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> addNewService(@Valid @RequestBody ServiceRequest request) {
        serviceService.addNewService(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/service/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateService(@Valid @RequestBody ServiceRequest request, @PathVariable(name = "id") Long id) {
        serviceService.updateService(request, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/service/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  ResponseEntity<Void> deleteService(@PathVariable(name = "id") Long id){
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/servicetype")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ServiceTypeResponse> getAllServiceType() {
        return serviceTypeService.getAllServiceType();
    }

    @GetMapping("/booking")
    public ResponseEntity<Page<BookingResponse>> getBookingPage(
            int pageOffset, int pageSize, String fromDate, Long salonId) {
        return ResponseEntity.ok(bookingService.getBookingPageByDateAndSalon(
                pageSize, pageOffset, fromDate, salonId));
    }

    @GetMapping("/salon/manager")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<List<SalonResponse>> getAllSalon() {
        return ResponseEntity.ok(salonService.getSalonByRole());
    }
}
