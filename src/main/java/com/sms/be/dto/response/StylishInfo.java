package com.sms.be.dto.response;

import java.time.LocalTime;

public interface StylishInfo {

    Long getId();

    String getName();

    Double getAvgRating();

    LocalTime getTime();

    int getDuration();

    String getAvatar();
}
