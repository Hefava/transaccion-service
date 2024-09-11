package com.bootcamp.transaccion_service.transaccion_service.ports.application.http.mapper;

import com.bootcamp.transaccion_service.transaccion_service.domain.model.Suministro;
import com.bootcamp.transaccion_service.transaccion_service.ports.application.http.dto.AgregarSuministroRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AgregarSuministroRequestMapper {

    @Mapping(source = "articuloID", target = "articuloID")
    @Mapping(source = "cantidad", target = "cantidad")
    Suministro toSuministro(AgregarSuministroRequest agregarSuministroRequest);
}
