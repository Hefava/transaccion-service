package com.bootcamp.transaccion_service.transaccion_service.domain.model;

import java.time.LocalDateTime;

public class Suministro {
    private Long suministroID;
    private Long articuloID;
    private Long cantidad;
    private LocalDateTime fechaCreacion;

    public Suministro() {
    }

    public Suministro(Long suministroID, Long articuloID, Long cantidad, LocalDateTime fechaCreacion) {
        this.suministroID = suministroID;
        this.articuloID = articuloID;
        this.cantidad = cantidad;
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getSuministroID() {
        return suministroID;
    }

    public void setSuministroID(Long suministroID) {
        this.suministroID = suministroID;
    }

    public Long getArticuloID() {
        return articuloID;
    }

    public void setArticuloID(Long articuloID) {
        this.articuloID = articuloID;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
