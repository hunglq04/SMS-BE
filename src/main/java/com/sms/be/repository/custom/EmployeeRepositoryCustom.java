package com.sms.be.repository.custom;

import com.sms.be.dto.ManagerInfoDto;
import com.sms.be.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryCustom {
    List<ManagerInfoDto> getAllManagerInfos();
    Optional<Employee> findEmployeeByIdAndRole(Long id, String role);
}
