package com.sms.be.service.impl;

import com.sms.be.dto.response.StylishInfo;
import com.sms.be.dto.response.StylishResponse;
import com.sms.be.model.Employee;
import com.sms.be.repository.BookingRepository;
import com.sms.be.repository.EmployeeRepository;
import com.sms.be.service.core.EmployeeService;
import org.springframework.beans.BeanUtils;
import com.sms.be.dto.ManagerInfoDto;
import com.sms.be.model.Employee;
import com.sms.be.repository.EmployeeRepository;
import com.sms.be.service.core.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BookingRepository bookingRepository;
    

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<StylishResponse> getStylishResponse() {
        List<Employee> lstEmployee = employeeRepository.findAll();
        List<StylishInfo> lstStylishInfo = bookingRepository.getStylishInfo();

        List<StylishResponse> lstStylishResponse = new ArrayList<>();

        for (Employee employee : lstEmployee) {
            StylishResponse stylishResponse = new StylishResponse();

            List<StylishInfo> lstStylish = lstStylishInfo.stream().filter(dt -> Objects.equals(dt.getId(), employee.getId())).collect(Collectors.toList());

            BeanUtils.copyProperties(employee, stylishResponse);
            stylishResponse.defineStylish();
            if (lstStylish.size() !=0 ) {
                lstStylish.forEach(dt -> stylishResponse.handleStylishTime(dt.getTime(), dt.getDuration()));
            }

            lstStylishResponse.add(stylishResponse);
        }

        return lstStylishResponse;
    }

    @Override
    public List<ManagerInfoDto> getAllManagerInfos() {
        return employeeRepository.getAllManagerInfos();
    }
}
