package com.bootcamp.transaccion_service.transaccion_service.ports.persistency.mysql.repository;

import com.bootcamp.transaccion_service.transaccion_service.ports.persistency.mysql.entity.SuministroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISuministroRepository extends JpaRepository<SuministroEntity, Long> {
}
