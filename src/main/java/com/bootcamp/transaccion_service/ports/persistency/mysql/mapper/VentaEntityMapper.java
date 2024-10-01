package com.bootcamp.transaccion_service.ports.persistency.mysql.mapper;

import com.bootcamp.transaccion_service.domain.model.Venta;
import com.bootcamp.transaccion_service.ports.persistency.mysql.entity.VentaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VentaEntityMapper {
    VentaEntity toVentaEntity(Venta venta);
    Venta toVenta(VentaEntity ventaEntity);
}
