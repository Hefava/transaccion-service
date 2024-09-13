package com.bootcamp.transaccion_service.ports.persistency.mysql.mapper;

import com.bootcamp.transaccion_service.domain.model.Suministro;
import com.bootcamp.transaccion_service.ports.persistency.mysql.entity.SuministroEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuministroEntityMapper {

    SuministroEntity toEntity(Suministro suministro);
    Suministro toSuministro(SuministroEntity suministroEntity);
}