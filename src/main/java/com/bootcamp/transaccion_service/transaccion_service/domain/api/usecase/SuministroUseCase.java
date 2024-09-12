package com.bootcamp.transaccion_service.transaccion_service.domain.api.usecase;

import com.bootcamp.transaccion_service.transaccion_service.domain.api.ISuministroServicePort;
import com.bootcamp.transaccion_service.transaccion_service.domain.spi.IUsuarioPersistencePort;
import com.bootcamp.transaccion_service.transaccion_service.domain.model.Suministro;
import com.bootcamp.transaccion_service.transaccion_service.domain.spi.IArticuloPersistencePort;
import com.bootcamp.transaccion_service.transaccion_service.domain.spi.ISuministroPersistencePort;

import java.time.LocalDateTime;

public class SuministroUseCase implements ISuministroServicePort {
    private final ISuministroPersistencePort suministroPersistencePort;
    private final IArticuloPersistencePort articuloPersistencePort;
    private final IUsuarioPersistencePort usuarioPersistencePort;

    public SuministroUseCase(ISuministroPersistencePort suministroPersistencePort, IArticuloPersistencePort articuloPersistencePort, IUsuarioPersistencePort usuarioPersistencePort) {
        this.suministroPersistencePort = suministroPersistencePort;
        this.articuloPersistencePort = articuloPersistencePort;
        this.usuarioPersistencePort = usuarioPersistencePort;
    }

    @Override
    public void agregarSuministro(Suministro suministro) {
        articuloPersistencePort.sumarExistencia(suministro.getArticuloID(), suministro.getCantidad());
        suministro.setFechaCreacion(LocalDateTime.now());
        suministro.setUsuarioID(usuarioPersistencePort.obtenerUsuarioID());
        suministroPersistencePort.agregarSuministro(suministro);
    }
}

