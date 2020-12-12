package com.sms.be.service.impl;

import com.sms.be.constant.CommonConstants;
import com.sms.be.dto.ManagerInfoDto;
import com.sms.be.dto.response.StylishInfo;
import com.sms.be.dto.response.StylishResponse;
import com.sms.be.dto.response.StylistSchedulerResponse;
import com.sms.be.exception.EmployeeNotFound;
import com.sms.be.model.Account;
import com.sms.be.model.Employee;
import com.sms.be.repository.BookingRepository;
import com.sms.be.repository.EmployeeRepository;
import com.sms.be.service.core.EmployeeService;
import com.sms.be.utils.MapperUtils;
import com.sms.be.utils.SecurityUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<StylishResponse> getStylishResponse(Long salonId, String date) {
        List<Employee> lstEmployee = employeeRepository.findAllBySalonAndRole(
                salonId, CommonConstants.ROLE_STYLIST);
        List<StylishInfo> lstStylishInfo = bookingRepository.getStylishInfo(LocalDate.parse(date));

        List<StylishResponse> lstStylishResponse = new ArrayList<>();

        for (Employee employee : lstEmployee) {
            StylishResponse stylishResponse = new StylishResponse();

            List<StylishInfo> lstStylish = lstStylishInfo.stream()
                    .filter(dt -> Objects.equals(dt.getId(), employee.getId()))
                    .collect(Collectors.toList());

            BeanUtils.copyProperties(employee, stylishResponse);
            stylishResponse.defineStylish();
            lstStylish.forEach(dt -> stylishResponse.handleStylishTime(dt.getTime(), dt.getDuration()));

            lstStylishResponse.add(stylishResponse);
        }

        return lstStylishResponse;
    }

    @Override
    public List<StylistSchedulerResponse> getStylistScheduler(Employee stylist, String strDate) {
        //get all booking of stylist with month between (month +1 and -1)
        LocalDate date = LocalDate.parse(strDate);
        return bookingRepository
                .findByStylistAndDateAfterAndDateBefore(ObjectUtils.defaultIfNull(stylist, getCurrentStylist()),
                        date.minusDays(40), date.plusDays(40)).stream()
                .map(MapperUtils::bookingToStylistSchedulerResponse).collect(Collectors.toList());
    }

    @Override
    public List<ManagerInfoDto> getAllManagerInfos() {
        return employeeRepository.getAllManagerInfos();
    }

    public Employee getCurrentStylist() {
        Account requester = SecurityUtils.getCurrentAccount();
        return employeeRepository.findByAccount(requester).orElseThrow(EmployeeNotFound::new);
    }
}
