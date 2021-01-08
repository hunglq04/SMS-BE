package com.sms.be.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.HashMap;

@RestController
@RequestMapping("/api/zp-integrate")
public class CallbackController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private String key2 = "uUfsWgfLkRLzq6W2uNXTCxrfxs51auny";
    private Mac HmacSHA256;

    public CallbackController() throws Exception  {
        HmacSHA256 = Mac.getInstance("HmacSHA256");
        HmacSHA256.init(new SecretKeySpec(key2.getBytes(), "HmacSHA256"));
    }

    @PostMapping("/callback")
    public String callback(@RequestBody String jsonStr) {
        JSONObject result = new JSONObject();

        try {
            JSONObject cbdata = new JSONObject(jsonStr);
            String dataStr = cbdata.getString("data");
            String reqMac = cbdata.getString("mac");

            cbdata.keySet().forEach(key -> logger.info("{} == {}", key, cbdata.get(key)));

            byte[] hashBytes = HmacSHA256.doFinal(dataStr.getBytes());
            String mac = DatatypeConverter.printHexBinary(hashBytes).toLowerCase();

            // kiểm tra callback hợp lệ (đến từ ZaloPay server)
            if (!reqMac.equals(mac)) {
                // callback không hợp lệ
                result.put("returncode", -1);
                result.put("returnmessage", "mac not equal");
            } else {
                // thanh toán thành công
                // merchant cập nhật trạng thái cho đơn hàng
                callbackSuccess();
                JSONObject data = new JSONObject(dataStr);
                logger.info("update order's status = success where apptransid = {}", data.getString("apptransid"));

                result.put("returncode", 1);
                result.put("returnmessage", "success");
            }
        } catch (Exception ex) {
            result.put("returncode", 0); // ZaloPay server sẽ callback lại (tối đa 3 lần)
            result.put("returnmessage", ex.getMessage());
        }

        // thông báo kết quả cho ZaloPay server
        return result.toString();
    }

    @GetMapping("/test/callback/success")
    public ResponseEntity<Void> callbackSuccess() {
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("notification", new HashMap<String, String>(){{
            put("title", "paid");
            put("body", "Đã thanh toán!");
        }});
        return pushNotificationTemplate(personJsonObject);
    }

    @GetMapping("/test/callback/fail")
    public ResponseEntity<Void> callbackFail() {
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("notification", new HashMap<String, String>(){{
            put("title", "fail");
            put("body", "Hóa đơn ZaloPay đã bị hủy!");
        }});
        return pushNotificationTemplate(personJsonObject);
    }

    private ResponseEntity<Void> pushNotificationTemplate(JSONObject personJsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization","key=AAAAy2Ca4-Q:APA91bG2luY9yTUpQ2MieM1YqaBZF-OcNDWslqVT"
                + "bhsZN02bBbFIpiCys7tjOVio7V-aiySjIuhyJYb1mXXAQMV0IMQbPFDajzU-0elEWVvt2iSxQhJOfqn32MsXIqWGc4FlUWy86Gvc");

        personJsonObject.put("to", "eKOHvOx0kVQx8q9xMGd6Hz:APA91bHAmHf3KsoHEmZtxziVHk_19l2ZmYdj-v8niUYWoVbUpU0ky5NfDFlb"
                + "1v1Z7GGSfRA_vGD2neUqX9TE73BIRC6aLEcd2UiC-jZmy-JpKvK3p0MGdGjGM9-iBLogi7YS4Ee02cIq");
        HttpEntity<String> request =
                new HttpEntity<>(personJsonObject.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate
                .postForEntity("https://fcm.googleapis.com/fcm/send", request, String.class);
        logger.info(stringResponseEntity.getBody());
        return ResponseEntity.noContent().build();
    }
}
