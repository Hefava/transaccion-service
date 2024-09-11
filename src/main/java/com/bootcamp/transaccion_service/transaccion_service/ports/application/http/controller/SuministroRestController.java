package com.bootcamp.transaccion_service.transaccion_service.ports.application.http.controller;

import com.bootcamp.transaccion_service.transaccion_service.domain.api.ISuministroServicePort;
import com.bootcamp.transaccion_service.transaccion_service.domain.model.Suministro;
import com.bootcamp.transaccion_service.transaccion_service.ports.application.http.dto.AgregarSuministroRequest;
import com.bootcamp.transaccion_service.transaccion_service.ports.application.http.mapper.AgregarSuministroRequestMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suministro")
@RequiredArgsConstructor
public class SuministroRestController {
    private final ISuministroServicePort suministroServicePort;
    private final AgregarSuministroRequestMapper agregarSuministroRequestMapper;

    @PostMapping("/agregar-suministro")
    public ResponseEntity<Void> agregarSuministro(
            @RequestHeader("Authorization") String token,
            @RequestBody @Parameter(description = "Agregar suministro", required = true) AgregarSuministroRequest agregarSuministroRequest) {

        // Limpia el prefijo "Bearer" y agrega el token al contexto de seguridad de Spring
        String cleanedToken = token.replace("Bearer ", "");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(null, cleanedToken, null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // Continuar con la lógica de agregar el suministro
        Suministro suministro = agregarSuministroRequestMapper.toSuministro(agregarSuministroRequest);
        suministroServicePort.agregarSuministro(suministro);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
