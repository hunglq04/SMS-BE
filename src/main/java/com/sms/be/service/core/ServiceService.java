package com.sms.be.service.core;

import com.sms.be.dto.response.ServiceBookingResponse;

import java.util.List;

public interface ServiceService {
    List<ServiceBookingResponse> getServiceForBooking();
}
