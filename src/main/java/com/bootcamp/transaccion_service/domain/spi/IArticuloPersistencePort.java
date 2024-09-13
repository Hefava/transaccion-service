package com.bootcamp.transaccion_service.domain.spi;

public interface IArticuloPersistencePort {
    void sumarExistencia(Long articuloID, Long cantidad);
}