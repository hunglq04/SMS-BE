package com.sms.be.service.impl;

import com.sms.be.dto.MailDto;
import com.sms.be.exception.SendMailFailException;
import com.sms.be.exception.SettingNotFoundException;
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
        String item = "";
        for (int i = 0; i < 2; i++) {
            item = item + "<!-- Item --> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"Margin:0;padding-top:5px;padding-bottom:10px;padding-left:20px;padding-right:20px;\"> <!--[if mso]><table width=\"560\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"178\" valign=\"top\"><![endif]--> <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td class=\"es-m-p0r es-m-p20b\" width=\"178\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;\"> <img src=\""+"TODO UPDATE HINH SP"+"\" alt=\""+"TODO UPDATE TEN SAN PHAM"+"\" class=\"adapt-img\" title=\""+"TODO UPDATE TEN SAN PHAM"+"\" width=\"125\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <!--[if mso]></td><td width=\"20\"></td><td width=\"362\" valign=\"top\"><![endif]--> <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td width=\"362\" align=\"left\" style=\"padding:0;Margin:0;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"padding:0;Margin:0;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:arial, 'helvetica neue' , helvetica, sans-serif;line-height:21px;color:#333333;\"> <br></p> <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;\" class=\"cke_show_border\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0;\"> "+"TODO UPDATE TEN SAN PHAM"+"</td> <td style=\"padding:0;Margin:0;text-align:center;\" width=\"60\">"+"TODO UPDATE SO LUONG"+" </td> <td style=\"padding:0;Margin:0;text-align:center;\" width=\"100\"> "+"TODO UPDATE DON GIA"+" VND</td> </tr> </tbody> </table> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:arial, 'helvetica neue' , helvetica, sans-serif;line-height:21px;color:#333333;\"> <br></p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <!--[if mso]></td></tr></table><![endif]--> </td> </tr> <!-- Line --> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"padding:0;Margin:0;padding-left:20px;padding-right:20px;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td width=\"560\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> <table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0px;border-bottom:1px solid #EFEFEF;background:rgba(0, 0, 0, 0) none repeat scroll 0% 0%;height:1px;width:100%;margin:0px;\"> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr>";
        }
//        System.out.println(item);
        String footer = " <!-- Price --> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"Margin:0;padding-top:5px;padding-left:20px;padding-bottom:30px;padding-right:40px;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td width=\"540\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"right\" style=\"padding:0;Margin:0;\"> <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:500px;\" class=\"cke_show_border\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\" align=\"right\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0;text-align:right;font-size:18px;line-height:27px;\"> Subtotal:</td> <td style=\"padding:0;Margin:0;text-align:right;font-size:18px;line-height:27px;\">"+"TODO UPDATE TONG TIEN"+" VND</td> </tr> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0;text-align:right;font-size:18px;line-height:27px;\"> Flat-rate Shipping: </td> <td style=\"padding:0;Margin:0;text-align:right;font-size:18px;line-height:27px;color:#D48344;\"> <strong>FREE</strong> </td> </tr> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0;text-align:right;font-size:18px;line-height:27px;\"> Discount:</td> <td style=\"padding:0;Margin:0;text-align:right;font-size:18px;line-height:27px;\"> 0</td> </tr> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0;text-align:right;font-size:18px;line-height:27px;\"> <strong>Order Total:</strong> </td> <td style=\"padding:0;Margin:0;text-align:right;font-size:18px;line-height:27px;color:#D48344;\"> <strong>"+"TODO UPDATE TONG TIEN"+" VND</strong> </td> </tr> </tbody> </table> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:arial, 'helvetica neue' , helvetica, sans-serif;line-height:21px;color:#333333;\"> <br></p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </div> </body>\n" +
                "\n" +
                "</html>";

        String content = "<!DOCTYPE html> <html lang=\"en\">\n" +
                "\n" +
                "<head> <meta charset=\"UTF-8\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\"> <title>Document</title> </head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html> <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html style=\"width:100%;font-family:'Times New Roman';-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\">\n" +
                "\n" +
                "<head> <meta charset=\"UTF-8\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\"> <title>Trigger newsletter</title> <!--[if (mso 16)]> <style type=\"text/css\"> a {text-decoration: none;} </style> <![endif]--> <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--> <link rel=\"shortcut icon\" type=\"image/png\" href=\"https://stripo.email/assets/img/favicon.png\"> <style type=\"text/css\"> @media only screen and (max-width:600px) {\n" +
                "\n" +
                " p, ul li, ol li, a { font-size: 16px !important; line-height: 150% !important }\n" +
                "\n" +
                " h1 { font-size: 30px !important; text-align: center; line-height: 120% !important }\n" +
                "\n" +
                " h2 { font-size: 26px !important; text-align: center; line-height: 120% !important }\n" +
                "\n" +
                " h3 { font-size: 20px !important; text-align: center; line-height: 120% !important }\n" +
                "\n" +
                " h1 a { font-size: 30px !important }\n" +
                "\n" +
                " h2 a { font-size: 26px !important }\n" +
                "\n" +
                " h3 a { font-size: 20px !important }\n" +
                "\n" +
                " .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size: 16px !important }\n" +
                "\n" +
                " .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size: 16px !important }\n" +
                "\n" +
                " .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size: 12px !important }\n" +
                "\n" +
                " *[class=\"gmail-fix\"] { display: none !important }\n" +
                "\n" +
                " .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align: center !important }\n" +
                "\n" +
                " .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align: right !important }\n" +
                "\n" +
                " .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align: left !important }\n" +
                "\n" +
                " .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display: inline !important }\n" +
                "\n" +
                " .es-button-border { display: block !important }\n" +
                "\n" +
                " a.es-button { font-size: 20px !important; display: block !important; border-left-width: 0px !important; border-right-width: 0px !important }\n" +
                "\n" +
                " .es-btn-fw { border-width: 10px 0px !important; text-align: center !important }\n" +
                "\n" +
                " .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width: 100% !important }\n" +
                "\n" +
                " .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width: 100% !important; max-width: 600px !important }\n" +
                "\n" +
                " .es-adapt-td { display: block !important; width: 100% !important }\n" +
                "\n" +
                " .adapt-img { width: 100% !important; height: auto !important }\n" +
                "\n" +
                " .es-m-p0 { padding: 0px !important }\n" +
                "\n" +
                " .es-m-p0r { padding-right: 0px !important }\n" +
                "\n" +
                " .es-m-p0l { padding-left: 0px !important }\n" +
                "\n" +
                " .es-m-p0t { padding-top: 0px !important }\n" +
                "\n" +
                " .es-m-p0b { padding-bottom: 0 !important }\n" +
                "\n" +
                " .es-m-p20b { padding-bottom: 20px !important }\n" +
                "\n" +
                " .es-mobile-hidden, .es-hidden { display: none !important }\n" +
                "\n" +
                " .es-desk-hidden { display: table-row !important; width: auto !important; overflow: visible !important; float: none !important; max-height: inherit !important; line-height: inherit !important }\n" +
                "\n" +
                " .es-desk-menu-hidden { display: table-cell !important }\n" +
                "\n" +
                " table.es-table-not-adapt, .esd-block-html table { width: auto !important }\n" +
                "\n" +
                " table.es-social { display: inline-block !important }\n" +
                "\n" +
                " table.es-social td { display: inline-block !important }\n" +
                "\n" +
                " .es-menu td a { font-size: 16px !important } }\n" +
                "\n" +
                " #outlook a { padding: 0; }\n" +
                "\n" +
                " .ExternalClass { width: 100%; }\n" +
                "\n" +
                " .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div { line-height: 100%; }\n" +
                "\n" +
                " .es-button { mso-style-priority: 100 !important; text-decoration: none !important; }\n" +
                "\n" +
                " a[x-apple-data-detectors] { color: inherit !important; text-decoration: none !important; font-size: inherit !important; font-family: inherit !important; font-weight: inherit !important; line-height: inherit !important; }\n" +
                "\n" +
                " .es-desk-hidden { display: none; float: left; overflow: hidden; width: 0; max-height: 0; line-height: 0; mso-hide: all; } </style> <meta property=\"og:title\" content=\"Trigger newsletter\" /> <meta property=\"og:url\" content=\"https://viewstripo.email/template/c4e515dd-89b7-41c2-9ee6-269e3763dabc\" /> <meta property=\"og:type\" content=\"article\" /> </head>\n" +
                "\n" +
                "<body style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\"> <div class=\"es-wrapper-color\" style=\"background-color:#EFEFEF; font-family: sans-serif;\"> <!--[if gte mso 9]> <v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\"> <v:fill type=\"tile\" color=\"#efefef\"></v:fill> </v:background> <![endif]--> <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td valign=\"top\" style=\"padding:0;Margin:0;\"> <table class=\"es-header\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;\"> <table class=\"es-header-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#27bece;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"Margin:0;padding-top:5px;padding-bottom:5px;padding-left:15px;padding-right:15px;\"> <!--[if mso]><table width=\"570\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"180\" valign=\"top\"><![endif]--> <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td class=\"es-m-p0r\" width=\"180\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td class=\"es-m-p0l es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-left:15px;\"> <a href=\"http://localhost:3000/\" target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:14px;text-decoration:underline;color:#999999;\"><img src=\"https://pokebizute15.000webhostapp.com/images/logo.png\" alt=\"DHPro logo\" title=\"DHPro logo\" width=\"118\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></a> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;\"> <table class=\"es-content-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:20px;padding-right:20px;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td width=\"560\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-radius:0px;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:15px;\"> <h1 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:'trebuchet ms', helvetica, sans-serif;font-size:30px;font-style:normal;font-weight:normal;color:#333333;\"> Thank you for your order<br> </h1> </td> </tr> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"Margin:0;padding-top:5px;padding-bottom:5px;padding-left:40px;padding-right:40px;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;\"> You'll recevice an email when your Items are shipped. If you have any questions, Call us 0987-654-321. <br> </p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;\"> <table class=\"es-content-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;padding-bottom:30px;\"> <!--[if mso]><table width=\"560\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"280\" valign=\"top\"><![endif]--> <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td class=\"es-m-p20b\" width=\"280\" align=\"left\" style=\"padding:0;Margin:0;\"> <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#FEF9EF;border-color:#EFEFEF;border-width:1px 0px 1px 1px;border-style:solid;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#fef9ef\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"Margin:0;padding-bottom:10px;padding-top:20px;padding-left:20px;padding-right:20px;\"> <h4 style=\"Margin:0;line-height:120%;mso-line-height-rule:exactly;font-family:'trebuchet ms', helvetica, sans-serif;\"> SUMMARY:</h4> </td> </tr> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:20px;padding-left:20px;padding-right:20px;\"> <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;\" class=\"cke_show_border\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\" align=\"left\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0;\"> <span style=\"font-size:14px;line-height:21px;\">Order #:</span> </td> <td style=\"padding:0;Margin:0;\"> <span style=\"font-size:14px;line-height:21px;\">"+"TODO UPDATE HOA DON ID"+"</span> </td> </tr> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0;\"> <span style=\"font-size:14px;line-height:21px;\">Order Date</span> </td> <td style=\"padding:0;Margin:0;\"> <span style=\"font-size:14px;line-height:21px;\">"+"TODO UPDATE NGAY MUA"+"</span> </td> </tr> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0;\"> <span style=\"font-size:14px;line-height:21px;\">Order Total:</span> </td> <td style=\"padding:0;Margin:0;\"> <span style=\"font-size:14px;line-height:21px;\">"+"TODO UPDATE TONG TIEN"+" VND</span> </td> </tr> </tbody> </table> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;\"> <br></p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <!--[if mso]></td><td width=\"0\"></td><td width=\"280\" valign=\"top\"><![endif]--> <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td width=\"280\" align=\"left\" style=\"padding:0;Margin:0;\"> <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#FEF9EF;border-width:1px;border-style:solid;border-color:#EFEFEF;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#fef9ef\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"Margin:0;padding-bottom:10px;padding-top:20px;padding-left:20px;padding-right:20px;\"> <h4 style=\"Margin:0;line-height:120%;mso-line-height-rule:exactly;font-family:'trebuchet ms', helvetica, sans-serif;\"> SHIPPING ADDRESS:<br></h4> </td> </tr> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:20px;padding-left:20px;padding-right:20px;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;\"> "+"TODO UPDATE TEN KHACH HANG"+"</p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;\"> "+ "TODO UPDATE DIA CHI" +"</p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;\"></p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;\"> <table class=\"es-content-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\"> <tbody> <!-- Header --> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:20px;padding-right:20px;\"> <!--[if mso]><table width=\"560\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"270\" valign=\"top\"><![endif]--> <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td class=\"es-m-p0r es-m-p20b\" width=\"270\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"padding:0;Margin:0;padding-left:20px;\"> <h4 style=\"Margin:0;line-height:120%;mso-line-height-rule:exactly;font-family:'trebuchet ms', helvetica, sans-serif;\"> ITEMS ORDERED </h4> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <!--[if mso]></td><td width=\"20\"></td><td width=\"270\" valign=\"top\"><![endif]--> <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td width=\"270\" align=\"left\" style=\"padding:0;Margin:0;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"padding:0;Margin:0;\"> <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;\" class=\"cke_show_border\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0;\"> <span style=\"font-size:13px;\">NAME</span> </td> <td style=\"padding:0;Margin:0;text-align:center;\" width=\"60\"> <span style=\"font-size:13px;\"><span style=\"line-height:100%;\">QTY</span></span> </td> <td style=\"padding:0;Margin:0;text-align:center;\" width=\"100\"> <span style=\"font-size:13px;\"><span style=\"line-height:100%;\">PRICE</span></span> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <!--[if mso]></td></tr></table><![endif]--> </td> </tr> <!-- Line --> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"padding:0;Margin:0;padding-left:20px;padding-right:20px;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td width=\"560\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> <table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tbody> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0px;border-bottom:1px solid #EFEFEF;background:rgba(0, 0, 0, 0) none repeat scroll 0% 0%;height:1px;width:100%;margin:0px;\"> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr>";

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            String htmlMsg = setting.getValue1() + content + item + footer;
            helper.setSubject(setting.getValue2());
            message.setContent(htmlMsg, "text/html");
            helper.setTo(mailDto.getToMail());
            this.emailSender.send(message);
        } catch (MessagingException e) {
            throw new SendMailFailException(e);
        }
    }
}
