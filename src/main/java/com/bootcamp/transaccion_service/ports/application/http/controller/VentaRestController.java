package com.bootcamp.transaccion_service.ports.application.http.controller;

import com.bootcamp.transaccion_service.domain.api.IVentaServicePort;
import com.bootcamp.transaccion_service.domain.utils.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bootcamp.transaccion_service.domain.utils.SuministroConstants.VENTA_REALIZADA;
import static com.bootcamp.transaccion_service.domain.utils.SuministroValidationMessages.AUTHORIZATION;

@RestController
@RequestMapping("/venta")
@RequiredArgsConstructor
public class VentaRestController {

    private final IVentaServicePort ventaServicePort;

    @PostMapping("/realizar")
    public ResponseEntity<String> realizarVenta(@RequestHeader(AUTHORIZATION) String token) {
        TokenHolder.setToken(token);
        ventaServicePort.realizarVenta();
        TokenHolder.clear();
        return ResponseEntity.ok(VENTA_REALIZADA);
    }
}