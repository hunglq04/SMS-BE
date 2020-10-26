package com.sms.be.constant;

import lombok.Getter;

public enum ErrorMessage {
    PROVINCE_NOT_FOUND(404);

    @Getter
    private final int value;

    ErrorMessage(int value) {
        this.value = value;
    }
}
