package com.sms.be.service.core;

import com.sms.be.dto.ManagerInfoDto;
import com.sms.be.dto.response.StylishResponse;

import java.util.List;

public interface EmployeeService {
    List<ManagerInfoDto> getAllManagerInfos();
    public List<StylishResponse> getStylishResponse(Long salonId, String date);
}
