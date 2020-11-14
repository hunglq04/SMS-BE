package com.sms.be.repository.impl;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.constant.CommonConstants;
import com.sms.be.dto.ManagerInfoDto;
import com.sms.be.dto.QManagerInfoDto;
import com.sms.be.model.Employee;
import com.sms.be.model.QAccount;
import com.sms.be.model.QEmployee;
import com.sms.be.model.QRole;
import com.sms.be.repository.base.AbstractCustomQuery;
import com.sms.be.repository.custom.EmployeeRepositoryCustom;

import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl extends AbstractCustomQuery implements EmployeeRepositoryCustom {

    @Override
    public List<ManagerInfoDto> getAllManagerInfos() {
        QEmployee employee = new QEmployee("employee");
        QRole role = new QRole("employee_role");
        QAccount account = new QAccount("account");
        JPQLQuery<?> query = new JPAQuery<>(entityManager);
        return query.from(employee)
                .innerJoin(employee.account, account)
                .innerJoin(account.roles, role)
                .where(role.name.eq(CommonConstants.ROLE_MANAGER))
                .select(new QManagerInfoDto(employee.id, employee.name, employee.avatar, employee.idCard))
                .fetch();
    }

    @Override
    public Optional<Employee> findEmployeeByIdAndRole(Long id, String role) {
        QEmployee qEmployee = new QEmployee("employee");
        QRole qRole = new QRole("employee_role");
        QAccount qAccount = new QAccount("account");
        JPQLQuery<?> query = new JPAQuery<>(entityManager);
        Employee result = query.from(qEmployee)
                .innerJoin(qEmployee.account, qAccount)
                .innerJoin(qAccount.roles, qRole)
                .where(qRole.name.eq(role).and(qEmployee.id.eq(id)))
                .select(qEmployee)
                .fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public List<Employee> findAllBySalonAndRole(Long salonId, String role) {
        QEmployee employee = new QEmployee("employee");
        QRole qRole = new QRole("employee_role");
        QAccount account = new QAccount("account");
        JPQLQuery<?> query = new JPAQuery<>(entityManager);
        return query.from(employee)
                .innerJoin(employee.account, account)
                .innerJoin(account.roles, qRole)
                .where(qRole.name.eq(role))
                .where(employee.salon.id.eq(salonId))
                .select(employee)
                .fetch();
    }
}
