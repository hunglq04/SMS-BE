package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class ServiceTypeNotFound extends RestException {
    public ServiceTypeNotFound(String message) {
        super(message, ErrorMessage.SERVICE_TYPE_NOT_FOUND);
    }
}
