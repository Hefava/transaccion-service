package com.bootcamp.transaccion_service.infrastructure.exceptionhandler;

import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;

import static com.bootcamp.transaccion_service.domain.utils.SuministroConstants.STOCK_SERRVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleFeignException(ResponseStatusException ex) {
        return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<Map<String, String>> handleFeignRetryableException(RetryableException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Collections.singletonMap(MESSAGE,  STOCK_SERRVER_ERROR));
    }
}
