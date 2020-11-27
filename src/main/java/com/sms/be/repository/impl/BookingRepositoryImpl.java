package com.sms.be.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.model.*;
import com.sms.be.repository.base.AbstractCustomQuery;
import com.sms.be.repository.custom.BookingRepositoryCustom;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class BookingRepositoryImpl extends AbstractCustomQuery implements BookingRepositoryCustom {
    @Override
    public List<Booking> findBookingHistoryByCustomer(Customer customer) {
        return new JPAQuery<>(entityManager).from(QBooking.booking)
                .where(QBooking.booking.customer.eq(customer))
                .select(QBooking.booking)
                .fetch();
    }

    @Override
    public Page<Booking> getBookingPageFromDateBySalon(int pageSize, int pageOffset, String fromDate, Long salonId) {
        BooleanBuilder condition = new BooleanBuilder();
        LocalDateTime date = LocalDateTime.parse(fromDate);
        condition.and(QBooking.booking.date.goe(date.toLocalDate()));
        condition.and(QBooking.booking.time.goe(date.toLocalTime()));
        if (Objects.nonNull(salonId)) {
            condition.and(QBooking.booking.salon.id.eq(salonId));
        }
        JPQLQuery<Booking> query = new JPAQuery<>(entityManager)
                .from(QBooking.booking)
                .where(condition)
                .select(QBooking.booking);
        return getPage(query, pageOffset, pageSize);
    }
}
