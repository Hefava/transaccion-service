package com.bootcamp.transaccion_service.transaccion_service.ports.application.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloResponse {
    private Long articuloID;
    private Long cantidad;
}
