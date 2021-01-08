package com.sms.be.service;

import com.sms.be.dto.MailDto;
import com.sms.be.model.OrderDetail;

import javax.mail.MessagingException;
import java.util.List;

public interface ClientService {
    void sendMail(MailDto mailDto);
    void sendMailOrder(List<OrderDetail> orderDetails, String toEmail);
}
