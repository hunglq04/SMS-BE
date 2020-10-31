package com.sms.be.service.impl;

import com.sms.be.dto.response.SalonResponse;
import com.sms.be.model.District;
import com.sms.be.model.Salon;
import com.sms.be.repository.SalonRepository;
import com.sms.be.service.core.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SalonServiceImpl implements SalonService {

    @Autowired
    SalonRepository salonRepository;

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
}
