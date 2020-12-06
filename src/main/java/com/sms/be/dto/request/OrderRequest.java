package com.sms.be.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    @NotNull
    private String date;
    @NotNull
    private List<CartItem> item;
    @NotNull
    private Long total;
}
