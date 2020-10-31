package com.sms.be.dto.response;

import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalonResponse {

    private Long id;

    private String street;

    private String district;

    private String ward;

    private String province;
}
