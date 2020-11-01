package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class EmployeeNotFound extends RestException {
    public EmployeeNotFound(String message) {
        super(message, ErrorMessage.EMPLOYEE_NOT_FOUND);
    }
}
