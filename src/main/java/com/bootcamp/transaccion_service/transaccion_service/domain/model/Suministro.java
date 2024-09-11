package com.bootcamp.transaccion_service.transaccion_service.domain.model;

public class Suministro {
    private Long suministroID;
    private Long articuloID;
    private Long cantidad;

    public Suministro() {
    }

    public Suministro(Long suministroID, Long articuloID, Long cantidad) {
        this.suministroID = suministroID;
        this.articuloID = articuloID;
        this.cantidad = cantidad;
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
