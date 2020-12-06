package com.sms.be.service.core;

import com.sms.be.dto.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponse> getOrderDetailResponse(Long orderId);
}
