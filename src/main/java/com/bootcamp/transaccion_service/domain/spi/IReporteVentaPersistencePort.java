package com.bootcamp.transaccion_service.domain.spi;

import com.bootcamp.transaccion_service.domain.utils.ReporteVenta;

public interface IReporteVentaPersistencePort {
    void saveReporteVenta(ReporteVenta reporteVenta);
}
