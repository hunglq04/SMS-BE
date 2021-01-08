package com.sms.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalonStatisticDto {
    private Long revenue;
    private Long totalCustomer;
    private Long completedOrders;
    private Long progressOrders;
    private Long newOrders;
    private Map<String, Long> customerChart;
    private Map<String, Long> topServices;
    private Map<String, Long> orderChart;
    private Map<String, Integer> topProducts;
    private Map<String, Long> salonChart;
}
