package com.sms.be.service.impl;

import com.sms.be.dto.response.OrderDetailResponse;
import com.sms.be.model.Order;
import com.sms.be.model.OrderDetail;
import com.sms.be.repository.OrderDetailRepository;
import com.sms.be.repository.OrderRepository;
import com.sms.be.service.core.OrderDetailService;
import com.sms.be.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderRepository;
    @Override
    public List<OrderDetailResponse> getOrderDetailResponse(Long orderId) {
        List<OrderDetail> orderDetails = orderRepository.findByOrderId(orderId);
        return orderDetails.stream().map(MapperUtils::orderDetailToOrderDetailResponse)
                .collect(Collectors.toList());
    }
}
