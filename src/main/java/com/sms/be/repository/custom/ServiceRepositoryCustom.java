package com.sms.be.repository.custom;

import com.sms.be.model.Service;
import org.springframework.data.domain.Page;

public interface ServiceRepositoryCustom {
    Page<Service> getServicePage(int pageOffset, int pageSize);
}
