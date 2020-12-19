package com.sms.be.service;

import com.sms.be.dto.SalonStatisticDto;

public interface InternalService {
    SalonStatisticDto getSalonStatistic(Long salonId, String date, String monthYear, Integer year);
}
