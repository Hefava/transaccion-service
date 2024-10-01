package com.bootcamp.transaccion_service.ports.application.http.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.bootcamp.transaccion_service.domain.utils.SuministroConstants.ERROR_CANTIDAD;
import static com.bootcamp.transaccion_service.domain.utils.SuministroConstants.MIN_CANTIDAD;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgregarArticuloACarritoRequest {
    @NotNull
    private Long articuloID;

    @NotNull
    @Min(value = MIN_CANTIDAD, message = ERROR_CANTIDAD)
    private Long cantidad;
}
