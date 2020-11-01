package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;
import lombok.Getter;

public class RestException extends RuntimeException {

    @Getter
    private final ErrorMessage errorMessage;

    public RestException(String message, ErrorMessage errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public RestException(String message, ErrorMessage errorMessage, Throwable cause) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }
}
