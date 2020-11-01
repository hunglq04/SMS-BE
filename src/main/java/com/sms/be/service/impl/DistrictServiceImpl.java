package com.sms.be.service.impl;

import com.sms.be.dto.response.DistrictResponse;
import com.sms.be.exception.ProvinceNotFoundException;
import com.sms.be.model.Province;
import com.sms.be.repository.DistrictRepository;
import com.sms.be.repository.ProvinceRepository;
import com.sms.be.service.core.DistrictService;
import com.sms.be.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<DistrictResponse> getDistrictsAndWardsByProvinceId(Long provinceId)
            throws ProvinceNotFoundException {
        final Province province = provinceRepository.findById(provinceId)
                .orElseThrow(ProvinceNotFoundException::new);
        return districtRepository.findByProvince(province).stream()
                .map(MapperUtils::districtToDistrictResponse)
                .collect(Collectors.toList());
    }
}
