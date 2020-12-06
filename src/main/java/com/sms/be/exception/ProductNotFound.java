package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class ProductNotFound extends RestException {

    public ProductNotFound() {
        super("No product found", ErrorMessage.PRODUCT_NOT_FOUND);
    }

    public ProductNotFound(String message) {
        super(message, ErrorMessage.PRODUCT_NOT_FOUND);
    }
}
