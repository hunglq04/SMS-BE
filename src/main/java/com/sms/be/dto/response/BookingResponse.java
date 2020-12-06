package com.sms.be.dto.response;

import com.sms.be.constant.BookingStatus;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@Builder
public class BookingResponse {
    private Long bookingId;
    private SalonResponse salon;
    private String dateTime;
    private List<ServiceBookingResponse> services;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private String stylist;
    private CustomerResponse customer;
    private String walkInGuest;
}
