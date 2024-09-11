package com.bootcamp.transaccion_service.transaccion_service.ports.application.http.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgregarSuministroRequest {
    @NotNull
    private Long articuloID;

    @NotNull
    private Long cantidad;
}
