package com.bootcamp.transaccion_service.domain.utils;

public class Articulos {
    private String nombreArticulo;
    private Long cantidadComprada;
    private Double precioUnitario;

    public Articulos() {
    }

    public Articulos(String nombreArticulo, Long cantidadComprada, Double precioUnitario) {
        this.nombreArticulo = nombreArticulo;
        this.cantidadComprada = cantidadComprada;
        this.precioUnitario = precioUnitario;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public Long getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(Long cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
