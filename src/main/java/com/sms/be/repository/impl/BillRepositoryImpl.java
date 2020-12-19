package com.sms.be.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.repository.base.AbstractCustomQuery;
import com.sms.be.repository.custom.BillRepositoryCustom;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Year;
import java.util.Map;
import java.util.stream.Collectors;

import static com.querydsl.core.group.GroupBy.groupBy;

public class BillRepositoryImpl extends AbstractCustomQuery implements BillRepositoryCustom {

    @Override
    public Long getRevenueFromServices(Long salonId, String date, String monthYear, Integer year) {
        return new JPAQuery<Long>(entityManager).from(bill)
                .innerJoin(bill.booking, booking)
                .where(buildStatisticCondition(salonId, date, monthYear, year))
                .select(bill.total).fetch().stream()
                .reduce(Long::sum).orElse(0L);
    }

    @Override
    public Long countCustomers(Long salonId, String date, String monthYear, Integer year) {
        return new JPAQuery<Long>(entityManager).from(bill)
                .innerJoin(bill.booking, booking)
                .where(buildStatisticCondition(salonId, date, monthYear, year))
                .fetchCount();
    }

    @Override
    public Map<Integer, Long> groupRevenueFromServicesByDate(Long salonId, String date, String monthYear,
            Integer year) {
        NumberExpression<Integer> dateGroup = year != null ? bill.dateTime.yearMonth() : bill.dateTime.dayOfMonth();
        Map<Integer, Long> result = new JPAQuery<>(entityManager).from(bill).innerJoin(bill.booking, booking)
                .where(buildChartCondition(salonId, date, monthYear, year))
                .groupBy(dateGroup).select(dateGroup, bill.total.count())
                .transform(groupBy(dateGroup).as(bill.total.sum()));
        return StringUtils.isBlank(date) ? result :
                result.entrySet().stream().collect(Collectors
                        .toMap(e -> Year.of(LocalDate.parse(date).getYear()).atMonth(LocalDate.parse(date).getMonth())
                                .atDay(e.getKey()).getDayOfWeek().getValue(), Map.Entry::getValue));
    }

    @Override
    public Map<String, Long> groupTopServicesByDate(Long salonId, String date, String monthYear, Integer year) {
        return new JPAQuery<>(entityManager).from(service, bill)
                .where(bill.booking.services.contains(service))
                .where(buildChartCondition(salonId, date, monthYear, year))
                .groupBy(service.name)
                .select(service.name, service.count())
                .orderBy(service.count().desc())
                .transform(groupBy(service.name).as(service.count()));
    }

    private BooleanBuilder buildStatisticCondition(Long salonId, String date, String monthYear, Integer year) {
        BooleanBuilder conditions = new BooleanBuilder();
        conditions.and(buildDateCondition(date, monthYear, year, bill.dateTime));
        if (salonId != null) {
            conditions.and(bill.booking.salon.id.eq(salonId));
        }
        return conditions;
    }

    private BooleanBuilder buildChartCondition(Long salonId, String date, String monthYear, Integer year) {
        BooleanBuilder conditions = new BooleanBuilder();
        conditions.and(buildDateConditionForChart(date, monthYear, year, bill.dateTime));
        if (salonId != null) {
            conditions.and(bill.booking.salon.id.eq(salonId));
        }
        return conditions;
    }
}
