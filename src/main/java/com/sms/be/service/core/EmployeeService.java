package com.sms.be.service.core;

import com.sms.be.dto.ManagerInfoDto;
import com.sms.be.dto.response.EmployeeResponse;
import com.sms.be.dto.response.StylishResponse;
import com.sms.be.dto.response.StylistSchedulerResponse;
import com.sms.be.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<ManagerInfoDto> getAllManagerInfos();
    public List<StylishResponse> getStylishResponse(Long salonId, String date);
    Page<EmployeeResponse> getEmployeeBySalon(int pageOffset, int pageSize, Long salonId);
    List<StylistSchedulerResponse> getStylistScheduler(Employee stylist, String date);
}
