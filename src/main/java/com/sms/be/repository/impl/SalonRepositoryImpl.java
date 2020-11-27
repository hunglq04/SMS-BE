package com.sms.be.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.constant.CommonConstants;
import com.sms.be.dto.response.SalonInternalResponse;
import com.sms.be.model.*;
import com.sms.be.repository.SalonRepositoryCustom;
import com.sms.be.repository.base.AbstractCustomQuery;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class SalonRepositoryImpl extends AbstractCustomQuery implements SalonRepositoryCustom {

    private static final QSalon salon = QSalon.salon;
    private static final QEmployee employee = QEmployee.employee;

    @Override
    public Page<SalonInternalResponse> getSalonPage(int pageOffset, int pageSize) {
        JPAQuery<SalonInternalResponse> query = new JPAQuery<>(entityManager)
                .from(salon, employee)
                .where(salon.manager.eq(employee))
                .orderBy(salon.id.asc())
                .select(Projections.constructor(SalonInternalResponse.class,
                        salon.id, employee.name, salon.image, salon.street,
                        salon.ward.name, salon.district.name,
                        salon.province.name));
        return getPage(query, pageOffset,pageSize);
    }

    @Override
    public List<Salon> getSalonByRole(Employee employee) {
        BooleanBuilder condition = new BooleanBuilder();
        List<String> roles = employee.getAccount().getRoles().stream()
                .map(Role::getName).collect(Collectors.toList());
        if (!roles.contains(CommonConstants.ROLE_ADMIN)) {
            condition.and(salon.manager.eq(employee));
        }
        return new JPAQuery<>(entityManager)
                .from(salon)
                .where(condition)
                .select(salon)
                .fetch();
    }
}
