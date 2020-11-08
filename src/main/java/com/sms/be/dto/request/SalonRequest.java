package com.sms.be.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalonRequest {
    @NotNull
    private Long managerId;
    @NotNull
    private Long provinceId;
    @NotNull
    private Long districtId;
    @NotNull
    private String ward;
    @NotNull
    private String street;
    private String imageUrl;
}
