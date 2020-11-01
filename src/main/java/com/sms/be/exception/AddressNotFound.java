package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class AddressNotFound extends RestException {
    public AddressNotFound(String message) {
        super(message, ErrorMessage.ADDRESS_NOT_FOUND);
    }
}
