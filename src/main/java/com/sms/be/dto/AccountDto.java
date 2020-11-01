package com.sms.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    @NotNull
    @Size(min = 5)
    String username;
    @NotNull
    @Size(min = 5)
    String password;
}
