package com.sms.be.controller;

import com.sms.be.dto.request.BookingRequest;
import com.sms.be.dto.request.OrderRequest;
import com.sms.be.dto.response.BookingResponse;
import com.sms.be.dto.response.OrderResponse;
import com.sms.be.service.core.BookingService;
import com.sms.be.service.core.CustomerService;
import com.sms.be.service.core.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
}
