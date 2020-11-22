package com.sms.be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

public interface StylishInfo {

    Long getId();

    String getName();

    Double getAvgRating();

    LocalTime getTime();

    int getDuration();

    String getAvatar();
}
