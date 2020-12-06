package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class ProductTypeNotFound extends RestException {
    public ProductTypeNotFound(String message) {
        super(message, ErrorMessage.PRODUCT_TYPE_NOT_FOUND);
    }
}
