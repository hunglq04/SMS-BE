package com.sms.be.repository;

import com.sms.be.model.Booking;
import com.sms.be.repository.base.BaseRepository;
import com.sms.be.repository.custom.BookingRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends BaseRepository<Booking, Long>, BookingRepositoryCustom {
}
