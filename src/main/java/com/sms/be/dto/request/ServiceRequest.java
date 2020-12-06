package com.sms.be.dto.request;

import com.sms.be.model.Service;
import com.sms.be.model.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {
    @NotNull
    private Long serviceTypeId;
    @NotNull
    private String name;
    @NotNull
    private String bookingImage;
    @NotNull
    private String description;
    @NotNull
    private String bookingRecommendImage;
    @NotNull
    private Long price;
    @NotNull
    private Integer duration;
    @NotNull
    private boolean isRecommend = false;
}
