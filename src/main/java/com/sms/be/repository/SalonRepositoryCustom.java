package com.sms.be.repository;

import com.sms.be.dto.response.SalonInternalResponse;
import com.sms.be.model.Employee;
import com.sms.be.model.Salon;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SalonRepositoryCustom {
    Page<SalonInternalResponse> getSalonPage(int pageOffset, int pageSize);
    List<Salon> getSalonByRole(Employee employee);
}
