package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class SalonNotFoundException extends RestException {
    public SalonNotFoundException() {
        super("No salon found", ErrorMessage.SALON_NOT_FOUND);
    }

    public SalonNotFoundException(String message) {
        super(message, ErrorMessage.SALON_NOT_FOUND);
    }
}
