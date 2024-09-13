package com.bootcamp.transaccion_service.domain.spi;

import com.bootcamp.transaccion_service.domain.model.Suministro;

public interface ISuministroPersistencePort {
    void agregarSuministro(Suministro suministro);
}
