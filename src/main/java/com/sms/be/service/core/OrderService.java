package com.sms.be.service.core;

import com.sms.be.dto.request.OrderRequest;
import com.sms.be.dto.response.OrderResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getOrderHistorytByCustomer();
    void orderProducts(OrderRequest orderRequest);
    OrderResponse getOrderDetail(Long orderId);
    Page<OrderResponse> getOrderPageByDate(int pageSize, int pageOffset, String fromDate);
}
