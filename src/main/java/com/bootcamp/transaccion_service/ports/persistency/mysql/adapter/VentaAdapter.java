package com.bootcamp.transaccion_service.ports.persistency.mysql.adapter;

import com.bootcamp.transaccion_service.domain.model.Venta;
import com.bootcamp.transaccion_service.domain.spi.IVentaPersistencePort;
import com.bootcamp.transaccion_service.ports.persistency.mysql.entity.VentaEntity;
import com.bootcamp.transaccion_service.ports.persistency.mysql.mapper.VentaEntityMapper;
import com.bootcamp.transaccion_service.ports.persistency.mysql.repository.IVentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VentaAdapter implements IVentaPersistencePort {

    private final IVentaRepository ventaRepository;
    private final VentaEntityMapper ventaEntityMapper;

    @Override
    public Venta guardarVenta(Venta venta) {
        VentaEntity ventaEntity = ventaEntityMapper.toVentaEntity(venta);
        VentaEntity savedEntity = ventaRepository.save(ventaEntity);
        return ventaEntityMapper.toVenta(savedEntity);
    }

    @Override
    public Venta eliminarUltimaVenta() {
        VentaEntity ultimaVenta = ventaRepository.findTopByOrderByVentaIDDesc();
        ventaRepository.delete(ultimaVenta);
        return ventaEntityMapper.toVenta(ultimaVenta);
    }
}
