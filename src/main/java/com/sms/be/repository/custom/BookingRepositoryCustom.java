package com.sms.be.repository.custom;

import com.sms.be.model.Booking;
import com.sms.be.model.Customer;

import java.util.List;

public interface BookingRepositoryCustom {
    List<Booking> findBookingHistoryByCustomer(Customer customer);
}
