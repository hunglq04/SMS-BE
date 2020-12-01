package com.sms.be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalonResponse {

    private Long id;

    private String street;

    private String district;

    private String ward;

    private String province;

    private String image;

    List<StylishResponse> stylishResponses;

}
