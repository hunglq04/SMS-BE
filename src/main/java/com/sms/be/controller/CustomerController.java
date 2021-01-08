package com.sms.be.controller;

import com.sms.be.dto.request.BookingRequest;
import com.sms.be.dto.request.CustomerRequest;
import com.sms.be.dto.request.OrderRequest;
import com.sms.be.dto.request.ProductRequest;
import com.sms.be.dto.response.BookingResponse;
import com.sms.be.dto.response.OrderResponse;
import com.sms.be.dto.response.ProductResponse;
import com.sms.be.model.Customer;
import com.sms.be.service.core.BookingService;
import com.sms.be.service.core.CustomerService;
import com.sms.be.service.core.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/booking")
    public ResponseEntity<Void> bookServices(@Valid @RequestBody BookingRequest bookingRequest) {
        bookingService.bookServices(bookingRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/booking")
    public ResponseEntity<List<BookingResponse>> getBookHistory() {
        List<BookingResponse> response = bookingService.getBookingHistoryByCustomer();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/booking/{id}")
    public  ResponseEntity<Void> deleteBooking(@PathVariable(name = "id") Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderResponse>> getOrderHistory() {
        List<OrderResponse> response = orderService.getOrderHistorytByCustomer();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/detail")
    public ResponseEntity<OrderResponse> getOrderDetail(Long orderId) {
        OrderResponse orderDetailResponses = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(orderDetailResponses);
    }

    @PostMapping("/order")
    public ResponseEntity<Void> orderProducts(@Valid @RequestBody OrderRequest orderRequest) {
        orderService.orderProducts(orderRequest);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/customer")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Optional<Customer>> getCustomer() {
        Optional<Customer> customer = customerService.getcustomer();
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/customer")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerRequest customerRequest ){
        customerService.changeInfo(customerRequest);
        return ResponseEntity.noContent().build();
    }

}
