package com.sms.be.service.core;

import com.sms.be.dto.ManagerInfoDto;

import java.util.List;

public interface EmployeeService {
    List<ManagerInfoDto> getAllManagerInfos();
}
