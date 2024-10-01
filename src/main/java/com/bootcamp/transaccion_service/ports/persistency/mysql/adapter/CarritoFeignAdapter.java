package com.bootcamp.transaccion_service.ports.persistency.mysql.adapter;

import com.bootcamp.transaccion_service.domain.spi.ICarritoPersistencePort;
import com.bootcamp.transaccion_service.domain.utils.ArticuloCarritoInfo;
import com.bootcamp.transaccion_service.domain.utils.ArticuloCarritoInfoResponse;
import com.bootcamp.transaccion_service.domain.utils.ArticuloRequest;
import com.bootcamp.transaccion_service.ports.application.http.dto.AgregarArticuloACarritoRequest;
import com.bootcamp.transaccion_service.ports.application.http.dto.ArticuloCarritoResponseWrapper;
import com.bootcamp.transaccion_service.ports.application.http.dto.EliminarArticuloCarritoRequest;
import com.bootcamp.transaccion_service.ports.application.http.dto.InfoCarritoRequest;
import com.bootcamp.transaccion_service.ports.application.http.mapper.InfoCarritoRequestMapper;
import com.bootcamp.transaccion_service.ports.persistency.mysql.repository.ICarritoFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CarritoFeignAdapter implements ICarritoPersistencePort {

    private final ICarritoFeign carritoFeign;
    private final InfoCarritoRequestMapper infoCarritoRequestMapper;

    @Override
    public ArticuloCarritoInfoResponse getCarrito(ArticuloRequest request) {
        InfoCarritoRequest feignRequest = infoCarritoRequestMapper.toDto(request);

        ArticuloCarritoResponseWrapper responseWrapper = carritoFeign.obtenerCarrito(feignRequest);

        List<ArticuloCarritoInfo> articulos = responseWrapper.getContent().stream()
                .map(this::mapToDomain)
                .toList();

        return new ArticuloCarritoInfoResponse(
                responseWrapper.getPrecioTotal(),
                articulos,
                responseWrapper.getPage(),
                responseWrapper.getPageSize(),
                responseWrapper.getTotalPages(),
                responseWrapper.getTotalCount()
        );
    }

    @Override
    public void agregarArticuloACarrito(Long articuloId, Long cantidad) {
        AgregarArticuloACarritoRequest agregarArticuloACarritoRequest = new AgregarArticuloACarritoRequest();
        agregarArticuloACarritoRequest.setArticuloID(articuloId);
        agregarArticuloACarritoRequest.setCantidad(cantidad);
        carritoFeign.agregarArticuloACarrito(agregarArticuloACarritoRequest);
    }

    @Override
    public void eliminarArticuloDeCarrito(Long carritoId, Long articuloId) {
        EliminarArticuloCarritoRequest eliminarArticuloCarritoRequest = new EliminarArticuloCarritoRequest();
        eliminarArticuloCarritoRequest.setArticuloID(articuloId);
        carritoFeign.eliminarArticuloDeCarrito(eliminarArticuloCarritoRequest);
    }

    private ArticuloCarritoInfo mapToDomain(ArticuloCarritoResponseWrapper.ArticuloStockResponse dto) {
        return new ArticuloCarritoInfo(
                dto.getArticuloID(),
                dto.getNombre(),
                dto.getCantidad(),
                dto.getCantidadEnCarrito(),
                dto.getMensajeAbastecimiento(),
                dto.getPrecio(),
                dto.getMarcaNombre(),
                dto.getCategorias()
        );
    }
}
