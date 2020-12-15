package com.sms.be.service.impl;

import com.sms.be.dto.request.ServiceRequest;
import com.sms.be.dto.response.ServiceBookingResponse;
import com.sms.be.exception.EmployeeNotFound;
import com.sms.be.exception.ProductTypeNotFound;
import com.sms.be.exception.ServiceTypeNotFound;
import com.sms.be.model.Product;
import com.sms.be.model.Service;
import com.sms.be.model.ServiceType;
import com.sms.be.repository.ServiceRepository;
import com.sms.be.repository.ServiceTypeRepository;
import com.sms.be.service.core.ServiceService;
import com.sms.be.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@Transactional(rollbackFor = Throwable.class)
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Override
    public void addNewService(ServiceRequest serviceRequest) {
       ServiceType serviceType = serviceTypeRepository.findById(serviceRequest.getServiceTypeId())
               .orElseThrow(() -> new ServiceTypeNotFound("No service type found"));
       Service service = Service.builder()
               .name(serviceRequest.getName())
               .bookingImage(serviceRequest.getBookingImage())
               .price(serviceRequest.getPrice())
               .image(serviceRequest.getBookingImage())
               .description(serviceRequest.getDescription())
               .descriptionImage(serviceRequest.getDescriptionImage())
               .duration(serviceRequest.getDuration())
               .isRecommend(serviceRequest.isRecommend())
               .bookingRecommendImage(serviceRequest.getBookingRecommendImage())
               .serviceType(serviceType)
               .build();
       serviceRepository.save(service);
    }

    @Override
    public List<ServiceBookingResponse> getServiceForBooking() {
        List<Service> services = serviceRepository.findAll();
        return services.stream().map(MapperUtils::serviceToServiceBookingResponse)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<Service> getService(Long id){
        return serviceRepository.findById(id);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public void updateService(ServiceRequest serviceRequest, Long id) {
        ServiceType serviceType = serviceTypeRepository.findById(serviceRequest.getServiceTypeId())
                .orElseThrow(() -> new ServiceTypeNotFound("No service type found"));
        Optional<Service> services = serviceRepository.findById(id);
        services.get().setName(serviceRequest.getName());
        services.get().setImage(serviceRequest.getBookingImage());
        services.get().setBookingImage(serviceRequest.getBookingImage());
        services.get().setBookingRecommendImage(serviceRequest.getBookingRecommendImage());
        services.get().setDescription(serviceRequest.getDescription());
        services.get().setDescriptionImage(serviceRequest.getDescriptionImage());
        services.get().setPrice(serviceRequest.getPrice());
        services.get().setRecommend(serviceRequest.isRecommend());
        services.get().setDescription(serviceRequest.getDescription());
        services.get().setServiceType(serviceType);
        serviceRepository.save(services.get());
    }


}
