package com.sms.be.repository.custom;

import com.sms.be.dto.ManagerInfoDto;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<ManagerInfoDto> getAllManagerInfos();
}
