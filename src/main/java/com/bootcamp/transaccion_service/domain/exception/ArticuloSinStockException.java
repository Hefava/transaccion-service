package com.bootcamp.transaccion_service.domain.exception;

public class ArticuloSinStockException extends RuntimeException {
    private final String nombreArticulo;

    public ArticuloSinStockException(String nombreArticulo) {
        super();
        this.nombreArticulo = nombreArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }
}
