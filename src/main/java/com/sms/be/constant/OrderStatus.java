package com.sms.be.constant;

public enum OrderStatus {
    // User create new order
    NEW,
    // Wait for seller confirm
    PENDING,
    // Seller cancel order
    CANCELLED,
    // Seller confirm order
    CONFIRMED,
    // Order is shipping
    SHIPPING,
    // Order complete
    COMPLETED
}
