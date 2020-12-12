package com.sms.be.repository;

import com.sms.be.model.Bill;
import com.sms.be.model.Booking;
import com.sms.be.repository.base.BaseRepository;
import com.sms.be.repository.custom.BillRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends BaseRepository<Bill, Long>, BillRepositoryCustom {
    Optional<Bill> findByBooking(Booking booking);
}
