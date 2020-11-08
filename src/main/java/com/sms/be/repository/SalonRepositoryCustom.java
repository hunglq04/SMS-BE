package com.sms.be.repository;

import com.sms.be.dto.response.SalonInternalResponse;
import org.springframework.data.domain.Page;

public interface SalonRepositoryCustom {
    Page<SalonInternalResponse> getSalonPage(int pageOffset, int pageSize);
}
