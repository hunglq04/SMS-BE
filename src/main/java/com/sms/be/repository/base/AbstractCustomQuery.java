package com.sms.be.repository.base;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.jpa.JPQLQuery;
import com.sms.be.model.QBill;
import com.sms.be.model.QBooking;
import com.sms.be.model.QOrder;
import com.sms.be.model.QOrderDetail;
import com.sms.be.model.QProduct;
import com.sms.be.model.QService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public abstract class AbstractCustomQuery {
    
    @PersistenceContext
    protected EntityManager entityManager;

    // Query Objects
    protected final QBooking booking = QBooking.booking;
    protected final QBill bill = QBill.bill;
    protected final QOrder order = QOrder.order;
    protected final QService service = QService.service;
    protected final QProduct product = QProduct.product;
    protected final QOrderDetail orderDetail = QOrderDetail.orderDetail;

    protected <T> Page<T> getPage(JPQLQuery<T> query, int pageOffset, int pageSize) {
        Pageable pageable = PageRequest.of(pageOffset, pageSize);
        List<T> resultList = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long totalCount = query.fetchCount();
        return new PageImpl<>(resultList, pageable, totalCount);
    }

    protected BooleanBuilder buildDateCondition(String date, String monthYear, Integer year, DateTimePath<LocalDateTime> toCompare) {
        BooleanBuilder conditions = new BooleanBuilder();
        if (StringUtils.isNotBlank(date)) {
            LocalDateTime fromDate = LocalDateTime.of(LocalDate.parse(date), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(LocalDate.parse(date), LocalTime.MAX);
            conditions.and(toCompare.between(fromDate, toDate));
        } else {
            buildMonthYearCondition(monthYear, year, toCompare, conditions);
        }
        return conditions;
    }

    protected BooleanBuilder buildDateConditionForChart (String date, String monthYear, Integer year, DateTimePath<LocalDateTime> toCompare) {
        BooleanBuilder conditions = new BooleanBuilder();
        if (StringUtils.isNotBlank(date)) {
            LocalDateTime fromDate = LocalDateTime.of(LocalDate.parse(date).with(DayOfWeek.MONDAY), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(LocalDate.parse(date).with(DayOfWeek.MONDAY).plusDays(7), LocalTime.MIN);
            conditions.and(toCompare.between(fromDate, toDate));
        } else
            buildMonthYearCondition(monthYear, year, toCompare, conditions);
        return conditions;
    }

    private void buildMonthYearCondition(String monthYear, Integer year, DateTimePath<LocalDateTime> toCompare,
            BooleanBuilder conditions) {
        if (StringUtils.isNotBlank(monthYear) ) {
            LocalDateTime fromDate = LocalDateTime
                    .of(LocalDate.parse(monthYear).with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime
                    .of(LocalDate.parse(monthYear).with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
            conditions.and(toCompare.between(fromDate, toDate));
        } else if (year != null ) {
            LocalDateTime fromDate = LocalDateTime
                    .of(LocalDate.of(year, 1, 1).with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime
                    .of(LocalDate.of(year, 1, 1).with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX);
            conditions.and(toCompare.between(fromDate, toDate));
        }
    }
}
