package com.sms.be.repository;

import com.sms.be.model.Employee;
import com.sms.be.repository.base.BaseRepository;
import com.sms.be.repository.custom.EmployeeRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Long>, EmployeeRepositoryCustom {
}
