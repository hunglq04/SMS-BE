package com.sms.be.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;
}
