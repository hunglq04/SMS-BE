package com.sms.be.dto.response;

import com.sms.be.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    private Long orderId;
    private Product product;
    private Integer quantity;
    private Long price;
}
