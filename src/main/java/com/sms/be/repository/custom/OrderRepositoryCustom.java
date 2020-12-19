package com.sms.be.repository.custom;

import com.sms.be.model.Customer;
import com.sms.be.model.Employee;
import com.sms.be.model.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findOrderHistoryByCustomer(Customer customer);
    Page<Order> getOrderPageByDate(int pageSize, int pageOffset, String fromDate);
}
