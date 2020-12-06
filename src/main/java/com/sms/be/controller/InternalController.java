package com.sms.be.controller;

import com.sms.be.dto.ManagerInfoDto;
import com.sms.be.dto.request.BookingRequest;
import com.sms.be.dto.request.SalonRequest;
import com.sms.be.dto.response.BookingResponse;
import com.sms.be.dto.response.SalonInternalResponse;
import com.sms.be.dto.response.SalonResponse;
import com.sms.be.service.core.BookingService;
import com.sms.be.service.core.EmployeeService;
import com.sms.be.service.core.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
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

    @GetMapping("/booking")
    public ResponseEntity<Page<BookingResponse>> getBookingPage(
            int pageOffset, int pageSize, String fromDate, Long salonId) {
        return ResponseEntity.ok(bookingService.getBookingPageByDateAndSalon(
                pageSize, pageOffset, fromDate, salonId));
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
    public ResponseEntity<Long> invoice(@Valid @PathVariable(name = "id") Long bookingId) {
        return ResponseEntity.ok().body(bookingService.invoice(bookingId));
    }
}
