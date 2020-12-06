package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class OrderNotFound extends RestException{
    public  OrderNotFound(String message) {
        super(message, ErrorMessage.ORDER_NOT_FOUND);
    }
}
