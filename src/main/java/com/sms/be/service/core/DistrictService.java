package com.sms.be.service.core;

import com.sms.be.dto.response.DistrictResponse;
import com.sms.be.exception.ProvinceNotFoundException;

import java.util.List;

public interface DistrictService {
    List<DistrictResponse> getDistrictsAndWardsByProvinceId(Long provinceId) throws ProvinceNotFoundException;
}
