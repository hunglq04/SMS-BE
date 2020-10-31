package com.sms.be.service.impl;

import com.sms.be.dto.response.StylishInfo;
import com.sms.be.model.Employee;
import com.sms.be.repository.BookingRepository;
import com.sms.be.service.core.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public List<StylishInfo> getStylistViaDate() {
        return bookingRepository.getStylishInfo();
    }
}
