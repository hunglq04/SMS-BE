package com.sms.be.repository.custom;

import com.sms.be.model.Customer;
import com.sms.be.model.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findOrderHistoryByCustomer(Customer customer);
}
