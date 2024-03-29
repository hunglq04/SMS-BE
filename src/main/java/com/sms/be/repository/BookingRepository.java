package com.sms.be.repository;

import com.sms.be.constant.BookingStatus;
import com.sms.be.dto.response.StylishInfo;
import com.sms.be.model.Booking;
import com.sms.be.model.Employee;
import com.sms.be.repository.base.BaseRepository;
import com.sms.be.repository.custom.BookingRepositoryCustom;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends BaseRepository<Booking, Long>, BookingRepositoryCustom {
    @Query(value = "select e.id as id, e.name as name, e.avg_rating as avgRating , b.time as time, sum(s2.duration) as duration\n" +
            "from booking b inner join booking_service bs on b.id = bs.booking_id \n" +
            "inner join employee e on e.id = b.stylist_id\n" +
            "inner join service s2 on s2.id = bs.service_id \n" +
            "where b.\"date\" = :dateTime and b.status != 'CANCEL'\n" +
            "group by e.name, b.time, e.avg_rating, e.id",
            nativeQuery = true)
    List<StylishInfo> getStylishInfo(LocalDate dateTime);

    void deleteAllByIdIn(List<Long> bookingIds);

    List<Booking> findByStylistAndDateAfterAndDateBefore(Employee stylist, LocalDate after, LocalDate before);

    List<Booking> findByNotifiedIsFalseAndDateAndTimeBetweenAndStatus(LocalDate date, LocalTime from, LocalTime to,
            BookingStatus bookingStatus);

    List<Booking> findByStatusAndDateLessThanEqual(BookingStatus status, LocalDate date);
}
