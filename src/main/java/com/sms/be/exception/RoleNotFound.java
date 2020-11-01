package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class RoleNotFound extends RestException {
    public RoleNotFound() {
        super("Role not found", ErrorMessage.ROLE_NOT_FOUND);
    }
}
