package com.sms.be.service.core;

import com.sms.be.dto.request.SalonRequest;
import com.sms.be.dto.response.SalonInternalResponse;
import org.springframework.data.domain.Page;

public interface SalonService {
    void addNewSalon(SalonRequest salonRequest);
    Page<SalonInternalResponse> getSalonPage(int pageOffset, int pageSize);
}
