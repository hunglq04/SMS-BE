package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class SettingNotFoundException extends RestException {

    public SettingNotFoundException(String key) {
        super("Setting " + key + " not found", ErrorMessage.SETTING_NOT_FOUND);
    }
}
