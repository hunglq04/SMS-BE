package com.sms.be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private int statusCode;
    private String message;
    private String path;
    private LocalDateTime timestamp;
}
