package com.bootcamp.transaccion_service.ports.persistency.mysql.adapter;

import com.bootcamp.transaccion_service.domain.spi.IArticuloPersistencePort;
import com.bootcamp.transaccion_service.ports.persistency.mysql.repository.IArticuloFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticuloAdapter implements IArticuloPersistencePort {

    private final IArticuloFeign articuloFeign;

    @Override
    public void sumarExistencia(Long articuloID, Long cantidad) {
        articuloFeign.agregarCantidadArticulo(articuloID, cantidad);
    }
}