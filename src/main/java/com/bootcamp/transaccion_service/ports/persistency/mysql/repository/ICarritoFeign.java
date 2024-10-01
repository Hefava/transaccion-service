package com.bootcamp.transaccion_service.ports.persistency.mysql.repository;

import com.bootcamp.transaccion_service.ports.application.http.dto.AgregarArticuloACarritoRequest;
import com.bootcamp.transaccion_service.ports.application.http.dto.ArticuloCarritoResponseWrapper;
import com.bootcamp.transaccion_service.ports.application.http.dto.EliminarArticuloCarritoRequest;
import com.bootcamp.transaccion_service.ports.application.http.dto.InfoCarritoRequest;
import com.bootcamp.transaccion_service.ports.feign.FeingClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "carrito-service", url = "http://localhost:9010", configuration = FeingClientConfiguration.class)
public interface ICarritoFeign {
    @PostMapping("/carrito/obtener-articulos")
    ArticuloCarritoResponseWrapper obtenerCarrito(@RequestBody InfoCarritoRequest request);

    @DeleteMapping("/carrito/eliminar-articulo-de-carrito")
    void eliminarArticuloDeCarrito(@RequestBody EliminarArticuloCarritoRequest eliminarArticuloCarritoRequest);

    @PostMapping("/carrito/agregar-articulos")
    void agregarArticuloACarrito(@RequestBody AgregarArticuloACarritoRequest agregarArticuloACarritoRequest);
}
