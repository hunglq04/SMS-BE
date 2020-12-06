package com.sms.be.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.model.Customer;
import com.sms.be.model.Order;
import com.sms.be.model.QBooking;
import com.sms.be.model.QOrder;
import com.sms.be.repository.base.AbstractCustomQuery;
import com.sms.be.repository.custom.OrderRepositoryCustom;

import java.util.List;

public class OrderRepositoryImpl extends AbstractCustomQuery implements OrderRepositoryCustom {
    @Override
    public List<Order> findOrderHistoryByCustomer(Customer customer) {
        return new JPAQuery<>(entityManager).from(QOrder.order)
                .where(QOrder.order.customer.eq(customer))
                .select(QOrder.order)
                .fetch();
    }
}
