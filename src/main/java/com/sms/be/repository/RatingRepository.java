package com.sms.be.repository;

import com.sms.be.model.Booking;
import com.sms.be.model.Rating;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends BaseRepository<Rating, Long> {
    Optional<Rating> findFirstByBooking(Booking booking);
}
