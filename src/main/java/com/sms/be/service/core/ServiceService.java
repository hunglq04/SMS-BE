package com.sms.be.service.core;

import com.sms.be.dto.request.ProductRequest;
import com.sms.be.dto.request.ServiceRequest;
import com.sms.be.dto.response.ServiceBookingResponse;
import com.sms.be.model.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    void addNewService(ServiceRequest serviceRequest);
    List<ServiceBookingResponse> getServiceForBooking();
    Optional<Service> getService(Long id);
    void deleteService(Long id);
    void updateService(ServiceRequest serviceRequest, Long id);
}
