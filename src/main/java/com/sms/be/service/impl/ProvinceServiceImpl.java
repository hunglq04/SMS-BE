package com.sms.be.service.impl;

import com.sms.be.dto.response.ProvinceResponse;
import com.sms.be.repository.ProvinceRepository;
import com.sms.be.service.core.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<ProvinceResponse> getAllProvince() {
        return provinceRepository.findAll().stream()
                .map(province -> new ProvinceResponse(province.getId(), province.getName()))
                .collect(Collectors.toList());
    }
}
