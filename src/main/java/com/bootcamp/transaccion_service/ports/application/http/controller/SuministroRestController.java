package com.bootcamp.transaccion_service.ports.application.http.controller;

import com.bootcamp.transaccion_service.domain.api.ISuministroServicePort;
import com.bootcamp.transaccion_service.domain.model.Suministro;
import com.bootcamp.transaccion_service.domain.utils.SuministroValidationMessages;
import com.bootcamp.transaccion_service.domain.utils.TokenHolder;
import com.bootcamp.transaccion_service.ports.application.http.dto.AgregarSuministroRequest;
import com.bootcamp.transaccion_service.ports.application.http.mapper.AgregarSuministroRequestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bootcamp.transaccion_service.domain.utils.SuministroValidationMessages.AGREGAR_SUMINISTRO_DESCRIPTION;
import static com.bootcamp.transaccion_service.domain.utils.SuministroValidationMessages.AUTHORIZATION;

@RestController
@RequestMapping("/suministro")
@RequiredArgsConstructor
public class SuministroRestController {
    private final ISuministroServicePort suministroServicePort;
    private final AgregarSuministroRequestMapper agregarSuministroRequestMapper;

    @Operation(
            summary = SuministroValidationMessages.AUTH_SUMMARY_AGREGAR_SUMINISTRO,
            description = SuministroValidationMessages.AUTH_DESCRIPTION_AGREGAR_SUMINISTRO
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = SuministroValidationMessages.SUMINISTRO_AGREGADO_EXITO, content = @Content),
            @ApiResponse(responseCode = "401", description = SuministroValidationMessages.SUMINISTRO_CREDENCIALES_INCORRECTAS, content = @Content),
            @ApiResponse(responseCode = "500", description = SuministroValidationMessages.ERROR_INTERNO_SERVIDOR, content = @Content)
    })
    @PostMapping("/agregar-suministro")
    public ResponseEntity<Void> agregarSuministro(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestBody @Parameter(description = AGREGAR_SUMINISTRO_DESCRIPTION, required = true) AgregarSuministroRequest agregarSuministroRequest) {

        TokenHolder.setToken(token);
        Suministro suministro = agregarSuministroRequestMapper.toSuministro(agregarSuministroRequest);
        suministroServicePort.agregarSuministro(suministro);
        TokenHolder.clear();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Obtener Fecha de Abastecimiento",
            description = "Retorna la fecha en la que el artículo se abastecerá, 15 días después de la fecha actual."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fecha de abastecimiento calculada con éxito", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/fecha-abastecimiento")
    public ResponseEntity<String> obtenerFechaAbastecimiento() {
        String mensaje = suministroServicePort.obtenerFechaAbastecimiento();
        return ResponseEntity.ok(mensaje);
    }
}
