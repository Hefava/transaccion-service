package com.bootcamp.transaccion_service.ports.persistency.mysql.repository;

import com.bootcamp.transaccion_service.ports.persistency.mysql.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<VentaEntity, Long> {
    VentaEntity findTopByOrderByVentaIDDesc();
}
