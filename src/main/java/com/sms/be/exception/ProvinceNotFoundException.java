package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class ProvinceNotFoundException extends RestException {
    public ProvinceNotFoundException() {
        super("Province Not Found", ErrorMessage.PROVINCE_NOT_FOUND);
    }
}
