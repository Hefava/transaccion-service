package com.bootcamp.transaccion_service.infrastructure.exceptionhandler;

import com.bootcamp.transaccion_service.domain.exception.ArticuloSinStockException;
import com.bootcamp.transaccion_service.domain.exception.VentaFallidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;

import static com.bootcamp.transaccion_service.domain.utils.SuministroConstants.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleFeignException(ResponseStatusException ex) {
        return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
    }

    @ExceptionHandler(ArticuloSinStockException.class)
    public ResponseEntity<Map<String, String>> handleArticuloSinStockException(ArticuloSinStockException ex) {
        String mensaje = EL_ARTICULO + ex.getNombreArticulo() + NO_IENE_STOCK;
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", mensaje));
    }

    @ExceptionHandler(VentaFallidaException.class)
    public ResponseEntity<Map<String, String>> handleVentaFallidaException(VentaFallidaException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("message", ERROR_VENTA));
    }

}