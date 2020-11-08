package com.sms.be.service.impl;

import com.sms.be.dto.request.SalonRequest;
import com.sms.be.dto.response.SalonInternalResponse;
import com.sms.be.dto.response.SalonResponse;
import com.sms.be.exception.AddressNotFound;
import com.sms.be.exception.EmployeeNotFound;
import com.sms.be.model.Employee;
import com.sms.be.model.Salon;
import com.sms.be.model.Ward;
import com.sms.be.repository.EmployeeRepository;
import com.sms.be.repository.SalonRepository;
import com.sms.be.repository.WardRepository;
import com.sms.be.service.core.SalonService;
import com.sms.be.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SalonServiceImpl implements SalonService {

    @Autowired
    SalonRepository salonRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final String EMPTY_STR = " ";

    public List<SalonResponse> findAll()
    {
        final List<Salon> lstSalon = salonRepository.findAll();

        return lstSalon.stream().map(SalonServiceImpl::mapSalonResponse).collect(Collectors.toList());
    }

    public static SalonResponse mapSalonResponse(Salon salon) {
        String s = salon.getDistrict().getPrefix() + salon.getDistrict().getName();
        return new SalonResponse(salon.getId() ,salon.getStreet(), String.join(EMPTY_STR, salon.getDistrict().getPrefix(), salon.getDistrict().getName()),
                String.join(EMPTY_STR, salon.getWard().getPrefix(), salon.getWard().getName()), salon.getProvince().getName());
    }

    @Override
    public void addNewSalon(SalonRequest salonRequest) {
        Employee manager = employeeRepository.findById(salonRequest.getManagerId())
                .orElseThrow(() -> new EmployeeNotFound("No manager found"));
        String wardName = MapperUtils.wardNameWithPrefixToNameOnly(salonRequest.getWard());
        Ward ward = wardRepository.findByNameAndAndDistrictIdAndProvinceId(wardName,
                salonRequest.getDistrictId(), salonRequest.getProvinceId())
                .orElseThrow(() -> new AddressNotFound("No ward found"));
        Salon salon = Salon.builder()
                .manager(manager)
                .province(ward.getProvince())
                .district(ward.getDistrict())
                .ward(ward)
                .street(salonRequest.getStreet())
                .image(salonRequest.getImageUrl())
                .build();
        salonRepository.save(salon);
    }

    @Override
    public Page<SalonInternalResponse> getSalonPage(int pageOffset, int pageSize) {
        return salonRepository.getSalonPage(pageOffset, pageSize);
    }
}
