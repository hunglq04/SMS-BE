package com.sms.be.service.impl;

import com.sms.be.dto.response.ServiceTypeResponse;
import com.sms.be.repository.ServiceTypeRepository;
import com.sms.be.service.core.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ServiceTypeServiceImpl implements ServiceTypeService {
    @Autowired
    ServiceTypeRepository serviceTypeRepository;
    @Override
    public List<ServiceTypeResponse> getAllServiceType() {
        return serviceTypeRepository.findAll().stream()
                .map(serviceType -> new ServiceTypeResponse(serviceType.getId(), serviceType.getName()))
                .collect(Collectors.toList());
    }
}
