package com.sms.be.repository.custom;

import com.sms.be.model.Booking;
import com.sms.be.model.Customer;
import com.sms.be.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingRepositoryCustom {
    List<Booking> findBookingHistoryByCustomer(Customer customer);
    Page<Booking> getBookingPageFromDateBySalon(int pageSize, int pageOffset, String fromDate, Long salonId, Employee requester);
}
