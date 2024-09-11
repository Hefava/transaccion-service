package com.bootcamp.transaccion_service.transaccion_service.domain.api.usecase;

import com.bootcamp.transaccion_service.transaccion_service.domain.api.ISuministroServicePort;
import com.bootcamp.transaccion_service.transaccion_service.domain.model.Suministro;
import com.bootcamp.transaccion_service.transaccion_service.domain.spi.IArticuloPersistencePort;
import com.bootcamp.transaccion_service.transaccion_service.domain.spi.ISuministroPersistencePort;

public class SuministroUseCase implements ISuministroServicePort {
    private final ISuministroPersistencePort suministroPersistencePort;
    private final IArticuloPersistencePort articuloPersistencePort;

    public SuministroUseCase(ISuministroPersistencePort suministroPersistencePort, IArticuloPersistencePort articuloPersistencePort) {
        this.suministroPersistencePort = suministroPersistencePort;
        this.articuloPersistencePort = articuloPersistencePort;
    }

    @Override
    public void agregarSuministro(Suministro suministro) {
        articuloPersistencePort.sumarExistencia(suministro.getArticuloID(), suministro.getCantidad());
        suministroPersistencePort.agregarSuministro(suministro);
    }
}