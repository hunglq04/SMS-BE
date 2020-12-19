package com.sms.be.service.core;

import com.sms.be.dto.request.ServiceRequest;
import com.sms.be.dto.response.ServiceBookingResponse;
import com.sms.be.model.Service;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    void addNewService(ServiceRequest serviceRequest);
    List<ServiceBookingResponse> getServiceForBooking();
    Page<ServiceBookingResponse> getServicePage(int pageOffset, int pageSize);
    Optional<Service> getService(Long id);
    void deleteService(Long id);
    void updateService(ServiceRequest serviceRequest, Long id);
}
