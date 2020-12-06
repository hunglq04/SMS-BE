package com.sms.be.dto.response;

import com.sms.be.constant.OrderStatus;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long orderId;
    private String date;
    private String name;
    private String email;
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private List<OrderDetailResponse> products;
    private Long total;
    private CustomerResponse customer;
}
