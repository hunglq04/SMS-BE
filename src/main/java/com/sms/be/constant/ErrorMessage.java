package com.sms.be.constant;

import lombok.Getter;

public enum ErrorMessage {
    INVALID_INPUT(400),
    ACCESS_DENIED(401),
    CUSTOMER_NOT_FOUND(404),
    SALON_NOT_FOUND(404),
    PROVINCE_NOT_FOUND(404),
    PRODUCT_TYPE_NOT_FOUND(404),
    PRODUCT_NOT_FOUND(404),
    SERVICE_TYPE_NOT_FOUND(404),
    ROLE_NOT_FOUND(404),
    EMPLOYEE_NOT_FOUND(404),
    ADDRESS_NOT_FOUND(404),
    ORDER_NOT_FOUND(404),
    USERNAME_ALREADY_EXIST(409 ),
    UNEXPECTED_ERROR(500), ;
    @Getter
    private final int value;

    ErrorMessage(int value) {
        this.value = value;
    }
}
