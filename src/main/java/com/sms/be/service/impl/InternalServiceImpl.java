package com.sms.be.service.impl;

import com.sms.be.constant.OrderStatus;
import com.sms.be.dto.SalonStatisticDto;
import com.sms.be.repository.BillRepository;
import com.sms.be.repository.OrderRepository;
import com.sms.be.service.InternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class InternalServiceImpl implements InternalService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public SalonStatisticDto getSalonStatistic(Long salonId, String date, String monthYear, Integer year) {
        Map<String, Long> customerChart = billRepository.groupRevenueFromServicesByDate(salonId, date, monthYear, year)
                .entrySet().stream()
                .collect(Collectors.toMap(entry -> String.valueOf(entry.getKey() % 100), Map.Entry::getValue));
        Map<String, Long> orderChart = orderRepository.groupRevenueFromProductsByDate(salonId, date, monthYear, year)
                .entrySet().stream()
                .collect(Collectors.toMap(entry -> String.valueOf(entry.getKey() % 100), Map.Entry::getValue));
        Map<String, Long> salonChart = billRepository.groupRevenueFromSalonsByDate(date, monthYear, year).entrySet()
                .stream().collect(Collectors
                        .toMap(entry -> entry.getKey().getStreet() + ", " + entry.getKey().getProvince().getCode(),
                                Map.Entry::getValue));
        return SalonStatisticDto.builder().revenue(
                billRepository.getRevenueFromServices(salonId, date, monthYear, year) + orderRepository
                        .getRevenueFromProducts(date, monthYear, year))
                .totalCustomer(billRepository.countCustomers(salonId, date, monthYear, year))
                .completedOrders(orderRepository.countOrders(date, monthYear, year, OrderStatus.COMPLETED)).customerChart(customerChart)
                .topServices(billRepository.groupTopServicesByDate(salonId, date, monthYear, year))
                .orderChart(orderChart)
                .salonChart(salonChart)
                .topProducts(orderRepository.groupTopProductByDate(salonId, date, monthYear, year))
                .newOrders(orderRepository.countOrders(date, monthYear, year, OrderStatus.NEW))
                .progressOrders(orderRepository.countOrders(date, monthYear, year, OrderStatus.CONFIRMED)).build();
    }
}
