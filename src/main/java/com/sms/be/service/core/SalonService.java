package com.sms.be.service.core;

import com.sms.be.dto.request.SalonRequest;
import com.sms.be.dto.response.SalonInternalResponse;
import com.sms.be.dto.response.SalonResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SalonService {
    void addNewSalon(SalonRequest salonRequest);
    Page<SalonInternalResponse> getSalonPage(int pageOffset, int pageSize);
    List<SalonResponse> getSalonByRole();
}
