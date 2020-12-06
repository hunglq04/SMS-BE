package com.sms.be.dto.request;

import com.sms.be.dto.response.ProductResponse;
import com.sms.be.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {
    @NotNull
    private ProductResponse product;
    @NotNull
    private Integer quantity;
}
