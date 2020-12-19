package com.sms.be.repository.custom;

import java.util.Map;

public interface BillRepositoryCustom {
    Long getRevenueFromServices(Long salonId, String date, String monthYear, Integer year);
    Long countCustomers(Long salonId, String date, String monthYear, Integer year);
    Map<Integer, Long> groupRevenueFromServicesByDate(Long salonId, String date, String monthYear, Integer year);
    Map<String, Long> groupTopServicesByDate(Long salonId, String date, String monthYear, Integer year);
}
