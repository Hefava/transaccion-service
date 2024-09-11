package com.bootcamp.transaccion_service.transaccion_service.ports.persistency.mysql.repository;

import com.bootcamp.transaccion_service.transaccion_service.infrastructure.configuration.FeingClientConfiguration;
import com.bootcamp.transaccion_service.transaccion_service.ports.application.http.dto.ArticuloResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "stock-service", url = "http://localhost:8090", configuration = FeingClientConfiguration.class)
public interface IArticuloFeign {

    @PutMapping("/articulo/agregar-cantidad-articulo")
    ArticuloResponse agregarCantidadArticulo(@PathVariable Long articuloID, @PathVariable Long cantidad);
}