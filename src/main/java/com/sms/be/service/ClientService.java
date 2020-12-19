package com.sms.be.service;

import com.sms.be.dto.MailDto;

import javax.mail.MessagingException;

public interface ClientService {
    void sendMail(MailDto mailDto);
}
