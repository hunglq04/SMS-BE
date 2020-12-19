package com.sms.be.exception;

import com.sms.be.constant.ErrorMessage;

public class SendMailFailException extends RestException {

    public SendMailFailException(Throwable cause) {
        super("Send mail fail", ErrorMessage.SEND_MAIL_FAIL, cause);
    }
}
