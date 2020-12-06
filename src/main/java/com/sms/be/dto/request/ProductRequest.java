package com.sms.be.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotNull
    private Long productTypeId;
    @NotNull
    private String name;
    @NotNull
    private Long price;
    private String imageUrl;
    private String description;
}
