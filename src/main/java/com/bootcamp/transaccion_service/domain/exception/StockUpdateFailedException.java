package com.bootcamp.transaccion_service.domain.exception;

public class StockUpdateFailedException extends RuntimeException {
    public StockUpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
