package com.sms.be.dto.response;

import com.sms.be.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private int statusCode;
    @Enumerated(EnumType.STRING)
    private ErrorMessage error;
    private String message;
    private String path;
    private LocalDateTime timestamp;
}
