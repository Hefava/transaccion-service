package com.bootcamp.transaccion_service.domain.model;

import java.time.LocalDateTime;

public class Venta {
    private Long usuarioID;
    private Long cantidad;
    private LocalDateTime fechaCreacion;
    private Double precioTotal;

    public Venta() {
    }

    public Venta(Long usuarioID, Long cantidad, LocalDateTime fechaCreacion, Double precioTotal) {
        this.usuarioID = usuarioID;
        this.cantidad = cantidad;
        this.fechaCreacion = fechaCreacion;
        this.precioTotal = precioTotal;
    }

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
}