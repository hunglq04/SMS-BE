package com.sms.be.dto.response;

import com.sms.be.constant.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StylistSchedulerResponse {
    private Long id;
    private String customer;
    private List<ServiceBookingResponse> services;
    private String start;
    private String end;
    private BookingStatus status;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
}
