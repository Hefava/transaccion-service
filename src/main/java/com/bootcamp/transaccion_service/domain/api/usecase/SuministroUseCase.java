package com.bootcamp.transaccion_service.domain.api.usecase;

import com.bootcamp.transaccion_service.domain.api.ISuministroServicePort;
import com.bootcamp.transaccion_service.domain.spi.IUsuarioPersistencePort;
import com.bootcamp.transaccion_service.domain.model.Suministro;
import com.bootcamp.transaccion_service.domain.spi.IArticuloPersistencePort;
import com.bootcamp.transaccion_service.domain.spi.ISuministroPersistencePort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.bootcamp.transaccion_service.domain.utils.SuministroConstants.*;

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

    @Override
    public String obtenerFechaAbastecimiento() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaAbastecimiento;
        if (fechaActual.getDayOfMonth() < DIAS_ABASTECIMIENTO) {
            fechaAbastecimiento = fechaActual.withDayOfMonth(DIAS_ABASTECIMIENTO);
        } else {
            fechaAbastecimiento = fechaActual.plusMonths(MESES_ABASTECIMIENTO).withDayOfMonth(DIAS_ABASTECIMIENTO);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        return ARTICULO_ABASTECIMIENTO + fechaAbastecimiento.format(formatter);
    }

}