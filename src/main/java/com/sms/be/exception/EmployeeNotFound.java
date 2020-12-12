package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class EmployeeNotFound extends RestException {

    public EmployeeNotFound() {
        super("No employee found", ErrorMessage.EMPLOYEE_NOT_FOUND);
    }

    public EmployeeNotFound(String message) {
        super(message, ErrorMessage.EMPLOYEE_NOT_FOUND);
    }
}
