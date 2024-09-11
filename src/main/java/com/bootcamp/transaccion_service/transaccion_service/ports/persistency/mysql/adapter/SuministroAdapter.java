package com.bootcamp.transaccion_service.transaccion_service.ports.persistency.mysql.adapter;

import com.bootcamp.transaccion_service.transaccion_service.domain.model.Suministro;
import com.bootcamp.transaccion_service.transaccion_service.domain.spi.ISuministroPersistencePort;
import com.bootcamp.transaccion_service.transaccion_service.ports.persistency.mysql.mapper.SuministroEntityMapper;
import com.bootcamp.transaccion_service.transaccion_service.ports.persistency.mysql.repository.ISuministroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuministroAdapter implements ISuministroPersistencePort {
    private final ISuministroRepository suministroRepository;
    private final SuministroEntityMapper suministroEntityMapper;

    @Override
    @Transactional
    public void agregarSuministro(Suministro suministro) {
        suministroRepository.save(suministroEntityMapper.toEntity(suministro));
    }
}