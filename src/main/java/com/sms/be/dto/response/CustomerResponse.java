package com.sms.be.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private Long customerId;
    private String name;
    private String phone;
    private String email;
    private String address;
}
