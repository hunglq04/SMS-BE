package com.sms.be.service.core;

import com.sms.be.dto.response.ServiceTypeResponse;

import java.util.List;

public interface ServiceTypeService {
    List<ServiceTypeResponse> getAllServiceType();
}
