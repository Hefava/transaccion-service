package com.bootcamp.transaccion_service.domain.spi;

import com.bootcamp.transaccion_service.domain.model.Venta;

public interface IVentaPersistencePort {
    Venta guardarVenta(Venta venta);
    Venta eliminarUltimaVenta();
}
