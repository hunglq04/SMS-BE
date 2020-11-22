package com.sms.be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StylishResponse {

    private Long id;

    private String name;

    private Double avgRating;

    private String avatar;

    private HashMap<LocalTime, Boolean> stylishSchedule = new HashMap<>();

    public void defineStylish() {
        LocalTime beginMorningShiftTime = LocalTime.of(8, 0, 0);
        LocalTime endMorningShiftTime = LocalTime.of(12, 00, 0);
        while (beginMorningShiftTime.isBefore(endMorningShiftTime)) {
            stylishSchedule.put(beginMorningShiftTime, Boolean.TRUE);
            beginMorningShiftTime = beginMorningShiftTime.plusMinutes(30);
        }

        LocalTime beginEveningShiftTime = LocalTime.of(13, 0, 0);
        LocalTime endEveningShiftTime = LocalTime.of(21, 00, 0);
        while (beginEveningShiftTime.isBefore(endEveningShiftTime)) {
            stylishSchedule.put(beginEveningShiftTime, Boolean.TRUE);
            beginEveningShiftTime = beginEveningShiftTime.plusMinutes(30);
        }
    }

    public void handleStylishTime(LocalTime time, int duration) {
        LocalTime startTime = time;
        LocalTime endTime = time.plusMinutes(duration);
        while (startTime.isBefore(endTime)) {
            stylishSchedule.put(startTime, Boolean.FALSE);
            startTime = startTime.plusMinutes(30);
        }
    }
}
