package com.sms.be.service.impl;

import com.sms.be.dto.response.ServiceBookingResponse;
import com.sms.be.model.Service;
import com.sms.be.repository.ServiceRepository;
import com.sms.be.service.core.ServiceService;
import com.sms.be.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@Transactional(rollbackFor = Throwable.class)
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<ServiceBookingResponse> getServiceForBooking() {
        List<Service> services = serviceRepository.findAll();
        return services.stream().map(MapperUtils::serviceToServiceBookingResponse)
                .collect(Collectors.toList());
    }
}
