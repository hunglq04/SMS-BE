package com.sms.be.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull
    @Size(min = 5)
    private String username;
    @NotNull
    @Size(min = 5)
    private String password;
    @NotNull
    private List<String> roles;
}
