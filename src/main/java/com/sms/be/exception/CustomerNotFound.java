package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class CustomerNotFound extends RestException {
    public CustomerNotFound(String message) {
        super(message, ErrorMessage.INVALID_INPUT);
    }
}
