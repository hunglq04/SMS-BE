package com.sms.be.repository.custom;

import com.sms.be.model.Ward;

import java.util.Optional;

public interface WardRepositoryCustom {
    Optional<Ward> findByNameAndAndDistrictIdAndProvinceId(String name, Long districtId, Long provinceId);
}
