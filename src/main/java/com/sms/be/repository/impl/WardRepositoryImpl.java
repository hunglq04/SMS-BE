package com.sms.be.repository.impl;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.model.QWard;
import com.sms.be.model.Ward;
import com.sms.be.repository.base.AbstractCustomQuery;
import com.sms.be.repository.custom.WardRepositoryCustom;

import java.util.Optional;

public class WardRepositoryImpl extends AbstractCustomQuery implements WardRepositoryCustom {
    @Override
    public Optional<Ward> findByNameAndAndDistrictIdAndProvinceId(String name, Long districtId, Long provinceId) {
        QWard qWard = QWard.ward;
        Ward ward = new JPAQuery<Ward>(entityManager).from(qWard)
                .where(qWard.name.eq(name))
                .where(qWard.district.id.eq(districtId))
                .where(qWard.province.id.eq(provinceId))
                .select(qWard).fetchFirst();
        return Optional.ofNullable(ward);
    }
}
