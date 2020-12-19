package com.sms.be.service.impl;

import com.sms.be.dto.SalonStatisticDto;
import com.sms.be.repository.BillRepository;
import com.sms.be.repository.OrderRepository;
import com.sms.be.service.InternalService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .collect(Collectors.toMap(entry -> String.valueOf(entry.getKey()), Map.Entry::getValue));
        Map<String, Long> orderChart = orderRepository.groupRevenueFromProductsByDate(salonId, date, monthYear, year)
                .entrySet().stream()
                .collect(Collectors.toMap(entry -> String.valueOf(entry.getKey()), Map.Entry::getValue));
        return SalonStatisticDto.builder().revenue(
                billRepository.getRevenueFromServices(salonId, date, monthYear, year) + orderRepository
                        .getRevenueFromProducts(date, monthYear, year))
                .totalCustomer(billRepository.countCustomers(salonId, date, monthYear, year))
                .totalOrder(orderRepository.countOrders(date, monthYear, year)).customerChart(customerChart)
                .topServices(billRepository.groupTopServicesByDate(salonId, date, monthYear, year))
                .orderChart(orderChart)
                .topProducts(orderRepository.groupTopProductByDate(salonId, date, monthYear, year)).build();
    }
}
