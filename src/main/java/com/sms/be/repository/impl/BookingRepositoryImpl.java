package com.sms.be.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.constant.CommonConstants;
import com.sms.be.model.*;
import com.sms.be.repository.base.AbstractCustomQuery;
import com.sms.be.repository.custom.BookingRepositoryCustom;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookingRepositoryImpl extends AbstractCustomQuery implements BookingRepositoryCustom {
    @Override
    public List<Booking> findBookingHistoryByCustomer(Customer customer) {
        return new JPAQuery<>(entityManager).from(QBooking.booking)
                .where(QBooking.booking.customer.eq(customer))
                .select(QBooking.booking)
                .fetch();
    }

    @Override
    public Page<Booking> getBookingPageFromDateBySalon(int pageSize,
           int pageOffset, String fromDate, Long salonId, Employee employee) {
        BooleanBuilder condition = new BooleanBuilder();
        LocalDateTime date = LocalDateTime.parse(fromDate);
        condition.and(QBooking.booking.date.gt(date.toLocalDate()))
                .or(QBooking.booking.date.eq(date.toLocalDate())
                        .and(QBooking.booking.time.goe(date.toLocalTime())));
        List<String> roles = employee.getAccount().getRoles().stream()
                .map(Role::getName).collect(Collectors.toList());
        if (Objects.nonNull(salonId)) {
            condition.and(QBooking.booking.salon.id.eq(salonId));
        } else if (roles.contains(CommonConstants.ROLE_MANAGER) &&
                !roles.contains(CommonConstants.ROLE_ADMIN)) {
            condition.and(QBooking.booking.salon.manager.eq(employee));
        } else if (roles.contains(CommonConstants.ROLE_CASHIER) &&
                !roles.contains(CommonConstants.ROLE_ADMIN)) {
            condition.and(QBooking.booking.salon.eq(employee.getSalon()));
        }
        JPQLQuery<Booking> query = new JPAQuery<>(entityManager)
                .from(QBooking.booking)
                .where(condition)
                .orderBy(QBooking.booking.date.asc(),
                        QBooking.booking.time.asc(),
                        QBooking.booking.status.asc())
                .select(QBooking.booking);
        return getPage(query, pageOffset, pageSize);
    }
}
