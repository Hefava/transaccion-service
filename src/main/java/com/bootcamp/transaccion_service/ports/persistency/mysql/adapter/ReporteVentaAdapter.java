package com.bootcamp.transaccion_service.ports.persistency.mysql.adapter;

import com.bootcamp.transaccion_service.domain.spi.IReporteVentaPersistencePort;
import com.bootcamp.transaccion_service.domain.utils.ReporteVenta;
import com.bootcamp.transaccion_service.ports.application.http.dto.ReporteVentaRequest;
import com.bootcamp.transaccion_service.ports.application.http.mapper.ReporteVentaMapper;
import com.bootcamp.transaccion_service.ports.persistency.mysql.repository.IReporteFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReporteVentaAdapter implements IReporteVentaPersistencePort {
    private final IReporteFeign reporteFeign;
    private final ReporteVentaMapper reporteVentaMapper;

    @Override
    public void saveReporteVenta(ReporteVenta reporteVenta) {
        ReporteVentaRequest reporteVentaRequest = ReporteVentaMapper.toStaticDto(reporteVenta, reporteVentaMapper);
        reporteFeign.agregarArticuloACarrito(reporteVentaRequest);
    }
}