package com.sms.be.repository.custom;

import com.sms.be.dto.ManagerInfoDto;
import com.sms.be.model.Account;
import com.sms.be.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryCustom {
    List<ManagerInfoDto> getAllManagerInfos();
    Optional<Employee> findEmployeeByIdAndRole(Long id, String role);
    List<Employee> findAllBySalonAndRole(Long salonId, String roleStylist);
    Page<Employee> getEmployeeBySalon(int pageOffset, int pageSize, Long salonId);
}
