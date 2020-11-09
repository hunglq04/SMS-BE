package com.sms.be.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.dto.response.SalonInternalResponse;
import com.sms.be.model.QEmployee;
import com.sms.be.model.QSalon;
import com.sms.be.repository.SalonRepositoryCustom;
import com.sms.be.repository.base.AbstractCustomQuery;
import org.springframework.data.domain.Page;

public class SalonRepositoryImpl extends AbstractCustomQuery implements SalonRepositoryCustom {
    @Override
    public Page<SalonInternalResponse> getSalonPage(int pageOffset, int pageSize) {
        QSalon salon = QSalon.salon;
        QEmployee employee = QEmployee.employee;
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
}
