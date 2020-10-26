package com.sms.be.controller;

import com.sms.be.dto.response.ErrorResponse;
import com.sms.be.exception.RestException;
import com.sms.be.utils.Mapper;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse unexpectedException(Exception ex, WebRequest request) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURI(), LocalDateTime.now());
    }

    @ExceptionHandler({BadCredentialsException.class, ValidationException.class,
            IllegalArgumentException.class, InvalidDataAccessApiUsageException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse badCredentialException(Exception ex, WebRequest request) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURI(), LocalDateTime.now());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse accessDeniedException(Exception ex, WebRequest request) {
        return new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURI(), LocalDateTime.now());
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> restException(RestException re, ServletWebRequest request) {
        final HttpStatus httpStatus = Mapper.errorMessageToHttpStatus(re.getErrorMessage());
        final ErrorResponse body = ErrorResponse.builder()
                .message(re.getMessage())
                .statusCode(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequest().getRequestURI())
                .build();
        return  ResponseEntity.status(httpStatus).body(body);
    }
}
