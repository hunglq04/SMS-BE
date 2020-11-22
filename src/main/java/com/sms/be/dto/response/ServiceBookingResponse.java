package com.sms.be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceBookingResponse {
    private Long id;
    private String name;
    private String bookingImage;
    private String description;
    private String bookingRecommendImage;
    private Long price;
    private Integer duration;
    private String serviceType;
    private boolean isRecommend = false;
}
