package com.sms.be.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.model.Booking;
import com.sms.be.model.Customer;
import com.sms.be.model.Employee;
import com.sms.be.model.Order;
import com.sms.be.model.QBooking;
import com.sms.be.model.QOrder;
import com.sms.be.model.Role;
import com.sms.be.repository.base.AbstractCustomQuery;
import com.sms.be.repository.custom.OrderRepositoryCustom;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepositoryImpl extends AbstractCustomQuery implements OrderRepositoryCustom {
    @Override
    public List<Order> findOrderHistoryByCustomer(Customer customer) {
        return new JPAQuery<>(entityManager).from(QOrder.order)
                .where(QOrder.order.customer.eq(customer))
                .select(QOrder.order)
                .fetch();
    }

    @Override
    public Page<Order> getOrderPageByDate(int pageSize, int pageOffset, String fromDate) {
        BooleanBuilder condition = new BooleanBuilder();
        LocalDateTime date = LocalDateTime.parse(fromDate);
        condition.and(QOrder.order.date.gt(date.toLocalDate()))
                .or((QOrder.order.date.eq(date.toLocalDate())));
        JPQLQuery<Order> query = new JPAQuery<>(entityManager)
                .from(QOrder.order)
                .where(condition)
                .orderBy(QOrder.order.date.asc(),
                        QOrder.order.status.asc())
                .select(QOrder.order);
        return getPage(query, pageOffset, pageSize);
    }
}
