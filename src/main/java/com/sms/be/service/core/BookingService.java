package com.sms.be.service.core;

import com.sms.be.dto.request.BookingRequest;
import com.sms.be.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {
    void bookServices(BookingRequest bookingRequest);
    List<BookingResponse> getBookingHistoryByCustomer();
}
