package com.sms.be.repository.impl;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.model.QService;
import com.sms.be.model.Service;
import com.sms.be.repository.base.AbstractCustomQuery;
import com.sms.be.repository.custom.ServiceRepositoryCustom;
import org.springframework.data.domain.Page;

public class ServiceRepositoryImpl  extends AbstractCustomQuery implements ServiceRepositoryCustom {

    @Override
    public Page<Service> getServicePage(int pageOffset, int pageSize) {
        JPQLQuery<Service> query = new JPAQuery<>(entityManager)
                .from(QService.service)
                .orderBy(QService.service.id.asc())
                .select(QService.service);
        return getPage(query, pageOffset, pageSize);
    }
}
