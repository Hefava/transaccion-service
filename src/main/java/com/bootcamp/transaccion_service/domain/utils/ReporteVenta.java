package com.bootcamp.transaccion_service.domain.utils;

import java.time.LocalDateTime;
import java.util.List;

public class ReporteVenta {
    private String correoUsuario;
    private LocalDateTime fechaCompra;
    private Double costoTotal;
    private List<Articulos> articulosComprados;

    public ReporteVenta() {
    }

    public ReporteVenta(String correoUsuario, LocalDateTime fechaCompra, Double costoTotal, List<Articulos> articulosComprados) {
        this.correoUsuario = correoUsuario;
        this.fechaCompra = fechaCompra;
        this.costoTotal = costoTotal;
        this.articulosComprados = articulosComprados;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public List<Articulos> getArticulosComprados() {
        return articulosComprados;
    }

    public void setArticulosComprados(List<Articulos> articulosComprados) {
        this.articulosComprados = articulosComprados;
    }
}
