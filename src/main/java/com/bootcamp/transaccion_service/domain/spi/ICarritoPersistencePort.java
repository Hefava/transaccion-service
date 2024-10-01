package com.bootcamp.transaccion_service.domain.spi;

import com.bootcamp.transaccion_service.domain.utils.ArticuloCarritoInfoResponse;
import com.bootcamp.transaccion_service.domain.utils.ArticuloRequest;

public interface ICarritoPersistencePort {
    ArticuloCarritoInfoResponse getCarrito(ArticuloRequest request);
    void agregarArticuloACarrito(Long articuloId, Long cantidad);
    void eliminarArticuloDeCarrito(Long carritoId, Long articuloId);

}
