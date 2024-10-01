package com.bootcamp.transaccion_service.ports.application.http.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteVentaRequest {
    private String correoUsuario;
    private LocalDateTime fechaCompra;
    private Double costoTotal;
    private List<ArticulosRequest> articulosComprados;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticulosRequest {
        private String nombreArticulo;
        private Long cantidadComprada;
        private Double precioUnitario;
    }
}
