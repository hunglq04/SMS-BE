package com.sms.be.service.impl;

import com.sms.be.dto.MailDto;
import com.sms.be.exception.SendMailFailException;
import com.sms.be.exception.SettingNotFoundException;
import com.sms.be.model.OrderDetail;
import com.sms.be.model.Setting;
import com.sms.be.repository.SettingRepository;
import com.sms.be.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ClientServiceImpl implements ClientService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SettingRepository settingRepository;

    @Override
    public void sendMail(MailDto mailDto) {
        Setting setting = settingRepository.findFirstByKey(mailDto.getTemplateCode())
                .orElseThrow(() -> new SettingNotFoundException(mailDto.getTemplateCode()));
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            String htmlMsg = setting.getValue1();
            helper.setSubject(setting.getValue2());
            message.setContent(htmlMsg, "text/html; charset=utf-8");
            helper.setTo(mailDto.getToMail());
            this.emailSender.send(message);
        } catch (MessagingException e) {
            throw new SendMailFailException(e);
        }
    }
    @Override
    public void sendMailOrder(List<OrderDetail> orderDetails, String toEmail) {
        StringBuilder products = new StringBuilder();
        orderDetails.forEach(detail -> products.append("- ").append(detail.getProduct().getName())
                .append(" - ").append(detail.getQuantity())
                .append(" - ").append(detail.getPrice()).append("<br/>"));
        Optional<Setting> sendMailOrder = settingRepository.findFirstByKey("email.order");
        String mailOrder = sendMailOrder.map(setting -> setting.getValue1().replace("$$product$$", products.toString()))
                .orElseGet(() -> "Bạn đã đặt hàng thành công");
        String mailSubject = sendMailOrder.map(Setting::getValue2).orElseGet(() ->"Thông tin đơn hàng");
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setSubject(mailSubject);
            message.setContent(mailOrder, "text/html; charset=utf-8");
            helper.setTo(toEmail);
            this.emailSender.send(message);
        } catch (MessagingException e) {
            throw new SendMailFailException(e);
        }
    }
}
