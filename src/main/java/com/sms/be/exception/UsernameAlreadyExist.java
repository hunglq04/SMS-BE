package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class UsernameAlreadyExist extends RestException {
    public UsernameAlreadyExist(String message) {
        super(message, ErrorMessage.USERNAME_ALREADY_EXIST);
    }
}
