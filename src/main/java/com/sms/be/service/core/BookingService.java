package com.sms.be.service.core;

import com.sms.be.dto.RatingImageDto;
import com.sms.be.dto.request.BookingRequest;
import com.sms.be.dto.response.BookingResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingService {
    void bookServices(BookingRequest bookingRequest);
    List<BookingResponse> getBookingHistoryByCustomer();
    Page<BookingResponse> getBookingPageByDateAndSalon(int pageSize, int pageOffset, String fromDate, Long salonId);
    void deleteBooking(Long id);
    Long invoice(Long bookingId);
    BookingResponse startProgress(Long bookingId);
    void finishProgress(Long bookingId, RatingImageDto images);
}
