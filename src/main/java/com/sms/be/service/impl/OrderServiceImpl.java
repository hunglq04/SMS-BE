package com.sms.be.service.impl;

import com.sms.be.constant.OrderStatus;
import com.sms.be.dto.request.CartItem;
import com.sms.be.dto.request.OrderRequest;
import com.sms.be.dto.response.OrderDetailResponse;
import com.sms.be.dto.response.OrderResponse;
import com.sms.be.exception.CustomerNotFound;
import com.sms.be.exception.OrderNotFound;
import com.sms.be.exception.ProductNotFound;
import com.sms.be.model.*;
import com.sms.be.model.embedded.OrderDetailId;
import com.sms.be.repository.CustomerRepository;
import com.sms.be.repository.OrderDetailRepository;
import com.sms.be.repository.OrderRepository;
import com.sms.be.repository.ProductRepository;
import com.sms.be.service.core.OrderService;
import com.sms.be.utils.MapperUtils;
import com.sms.be.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderResponse> getOrderHistorytByCustomer() {
        Account requester = SecurityUtils.getCurrentAccount();
        Customer customer = customerRepository.findByAccount(requester)
                .orElseThrow(() -> new CustomerNotFound("No customer found"));
        List<Order> orders = orderRepository.findOrderHistoryByCustomer(customer);
        return orders.stream().map(order -> MapperUtils.orderToOrderResponse(order,
                orderDetailRepository.findByOrderId(order.getId()).stream()
                        .map(MapperUtils::orderDetailToOrderDetailResponse)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public void orderProducts(OrderRequest orderRequest) {
        Account requester = SecurityUtils.getCurrentAccount();
        Customer customer = customerRepository.findByAccount(requester)
                .orElseThrow(() -> new CustomerNotFound("No customer found"));
        Order oder = Order.builder()
                .date(LocalDate.parse(orderRequest.getDate()))
                .customer(customer)
                .name(orderRequest.getName())
                .email(orderRequest.getEmail())
                .phone(orderRequest.getPhone())
                .address(orderRequest.getAddress())
                .status(OrderStatus.NEW)
                .total(getTotalPrice(orderRequest.getItem()))
                .build();
        orderRepository.save(oder);
        orderRequest.getItem().forEach(cartItem -> saveCartItem(cartItem, oder));
    }

    @Override
    public OrderResponse getOrderDetail(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFound("No order found"));
        List<OrderDetailResponse> orderDetails = orderDetailRepository.findByOrderId(orderId).stream()
                .map(MapperUtils::orderDetailToOrderDetailResponse).collect(Collectors.toList());
        return MapperUtils.orderToOrderResponse(order, orderDetails);
    }

    @Override
    public Page<OrderResponse> getOrderPageByDate(int pageSize, int pageOffset, String fromDate) {
      return orderRepository.getOrderPageByDate(pageSize, pageOffset, fromDate).map(
              (Order order) -> MapperUtils.orderToOrderResponse(order, Collections.emptyList()));
    }

    private void saveCartItem(CartItem item, Order order) {
        Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow(ProductNotFound::new);
        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .price(item.getProduct().getPrice())
                .quantity(item.getQuantity())
                .build();
        orderDetail.setOrderDetailId(order.getId(), product.getId());
        orderDetailRepository.save(orderDetail);
    }

    private Long getTotalPrice(List<CartItem> cartItems) {
        return cartItems.stream().map(item -> item.getProduct().getPrice() * item.getQuantity())
                .reduce(Long::sum)
                .orElse(0L);
    }
}
