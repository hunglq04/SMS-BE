package com.sms.be.controller;

import com.sms.be.dto.ManagerInfoDto;
import com.sms.be.dto.RatingImageDto;
import com.sms.be.dto.SalonStatisticDto;
import com.sms.be.dto.request.BookingRequest;
import com.sms.be.dto.request.ProductRequest;
import com.sms.be.dto.request.SalonRequest;
import com.sms.be.dto.request.ServiceRequest;
import com.sms.be.dto.response.BillResponse;
import com.sms.be.dto.response.BookingResponse;
import com.sms.be.dto.response.EmployeeResponse;
import com.sms.be.dto.response.OrderResponse;
import com.sms.be.dto.response.ProductResponse;
import com.sms.be.dto.response.ProductTypeReponse;
import com.sms.be.dto.response.SalonInternalResponse;
import com.sms.be.dto.response.SalonResponse;
import com.sms.be.dto.response.ServiceBookingResponse;
import com.sms.be.dto.response.ServiceTypeResponse;
import com.sms.be.dto.response.StylistSchedulerResponse;
import com.sms.be.dto.response.*;
import com.sms.be.model.Product;
import com.sms.be.model.Salon;
import com.sms.be.model.Service;
import com.sms.be.service.InternalService;
import com.sms.be.service.core.BookingService;
import com.sms.be.service.core.EmployeeService;
import com.sms.be.service.core.OrderService;
import com.sms.be.service.core.ProductService;
import com.sms.be.service.core.ProductTypeService;
import com.sms.be.service.core.SalonService;
import com.sms.be.service.core.ServiceService;
import com.sms.be.service.core.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private InternalService internalService;

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

    @PutMapping("/salon/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateSalon(@Valid @RequestBody SalonRequest request, @PathVariable(name = "id") Long id) {
        salonService.updateSalon(request, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/salon/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SalonInfoResponse> getSalonInfo(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(salonService.getSalonInfo(id));
    }

    @GetMapping("/salon")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<SalonInternalResponse>> getSalonPage(int pageOffset, int pageSize) {
        return ResponseEntity.ok(salonService.getSalonPage(pageOffset,
                pageSize));
    }

    @GetMapping("/salon/statistic")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SalonStatisticDto> getSalonStatistic(
            @RequestParam(name = "salonId", required = false) Long salonId,
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(value = "monthYear", required = false) String monthYear,
            @RequestParam(value = "year", required = false) Integer year) {
        return ResponseEntity.ok().body(internalService.getSalonStatistic(salonId, date, monthYear, year));
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

    @GetMapping("/service")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<ServiceBookingResponse>> getServicePage(int pageOffset, int pageSize) {
        return ResponseEntity.ok(serviceService.getServicePage(pageOffset,
                pageSize));
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
    @GetMapping("/employee")
    public ResponseEntity<Page<EmployeeResponse>> getEmployeePage(
            int pageOffset, int pageSize, Long salonId) {
        return ResponseEntity.ok(employeeService.getEmployeeBySalon(
                pageSize, pageOffset, salonId));
    }

    @GetMapping("/salon/manager")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_CASHIER')")
    public ResponseEntity<List<SalonResponse>> getAllSalon() {
        return ResponseEntity.ok(salonService.getSalonByRole());
    }

    @PostMapping("/booking")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_CASHIER')")
    public ResponseEntity<Void> booking(@Valid @RequestBody BookingRequest bookingRequest) {
        bookingService.bookServices(bookingRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/booking/{id}/invoice")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_CASHIER')")
    public ResponseEntity<BillResponse> invoice(@Valid @PathVariable(name = "id") Long bookingId) {
        return ResponseEntity.ok().body(bookingService.invoice(bookingId));
    }

    @PostMapping("/booking/{id}/start")
    public ResponseEntity<BookingResponse> startProgress(@Valid @PathVariable(name = "id") Long bookingId) {
        return ResponseEntity.ok().body(bookingService.startProgress(bookingId));
    }

    @PostMapping("/booking/{id}/finish")
    public ResponseEntity<Void> finishProgress(@Valid @PathVariable(name = "id") Long bookingId, @RequestBody
            RatingImageDto images) {
        bookingService.finishProgress(bookingId, images);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("employee/stylist/scheduler")
    public ResponseEntity<List<StylistSchedulerResponse>> getStylistScheduler(String date) {
        return ResponseEntity.ok().body(employeeService.getStylistScheduler(null, date));
    }
    @GetMapping("order")
    public ResponseEntity<Page<OrderResponse>> getOrderPage(
            int pageOffset, int pageSize, String fromDate) {
        return ResponseEntity.ok(orderService.getOrderPageByDate(
                pageSize, pageOffset, fromDate));
    }
        @GetMapping("order/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetailPage(Long orderId) {
        return ResponseEntity.ok(orderService.getOrderDetail(orderId));
    }

    @PostMapping("/order/{orderId}/confirm")
    public ResponseEntity<OrderResponse> confirmOrder(@Valid @PathVariable(name = "orderId") Long orderId) {
        return ResponseEntity.ok().body(orderService.confirmOrder(orderId));
    }

    @PostMapping("/order/{orderId}/confirmcompleted")
    public ResponseEntity<OrderResponse> confirmCompletedOrder(@Valid @PathVariable(name = "orderId") Long orderId) {
        return ResponseEntity.ok().body(orderService.confirmCompletedOrder(orderId));
    }

}
