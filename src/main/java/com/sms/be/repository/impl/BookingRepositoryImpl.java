package com.sms.be.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.model.*;
import com.sms.be.repository.base.AbstractCustomQuery;
import com.sms.be.repository.custom.BookingRepositoryCustom;

import java.util.List;

public class BookingRepositoryImpl extends AbstractCustomQuery implements BookingRepositoryCustom {
    @Override
    public List<Booking> findBookingHistoryByCustomer(Customer customer) {
        return new JPAQuery<>(entityManager).from(QBooking.booking)
                .where(QBooking.booking.customer.eq(customer))
                .select(QBooking.booking)
                .fetch();
    }
}
