package com.sms.be.repository.custom;

import com.sms.be.model.Customer;
import com.sms.be.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderRepositoryCustom {
    List<Order> findOrderHistoryByCustomer(Customer customer);
    Long getRevenueFromProducts(String date, String monthYear, Integer year);
    Long countOrders(String date, String monthYear, Integer year);
    Map<Integer, Long> groupRevenueFromProductsByDate(Long salonId, String date, String monthYear, Integer year);
    Map<String, Integer> groupTopProductByDate(Long salonId, String date, String monthYear, Integer year);
}
