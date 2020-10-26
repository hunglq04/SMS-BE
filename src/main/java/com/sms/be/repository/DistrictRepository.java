package com.sms.be.repository;

import com.sms.be.model.District;
import com.sms.be.model.Province;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends BaseRepository<District, Long> {
    List<District> findByProvince(Province province);
}
