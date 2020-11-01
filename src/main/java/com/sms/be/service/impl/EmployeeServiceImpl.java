package com.sms.be.service.impl;

import com.sms.be.dto.ManagerInfoDto;
import com.sms.be.model.Employee;
import com.sms.be.repository.EmployeeRepository;
import com.sms.be.service.core.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<ManagerInfoDto> getAllManagerInfos() {
        return employeeRepository.getAllManagerInfos();
    }
}
