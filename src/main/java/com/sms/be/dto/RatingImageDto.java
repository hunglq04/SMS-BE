package com.sms.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingImageDto {
    private String image1;
    private String image2;
    private String image3;
    private String image4;
}
