package com.bootcamp.transaccion_service.ports.persistency.mysql.repository;

import com.bootcamp.transaccion_service.ports.application.http.dto.ReporteVentaRequest;
import com.bootcamp.transaccion_service.ports.feign.FeingClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "reporte-service", url = "http://localhost:9020", configuration = FeingClientConfiguration.class)
public interface IReporteFeign {
    @PostMapping("/reporte/generar-reporte-ventas")
    void agregarArticuloACarrito(@RequestBody ReporteVentaRequest reporteVentaRequest);
}
