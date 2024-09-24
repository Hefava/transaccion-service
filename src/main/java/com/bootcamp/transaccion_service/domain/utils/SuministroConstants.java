package com.bootcamp.transaccion_service.domain.utils;

public class SuministroConstants {
    private SuministroConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String FORMATO_FECHA = "yyyy-MM-dd";
    public static final String ARTICULO_ABASTECIMIENTO = "El artículo se abastecerá el: ";
    public static final Integer DIAS_ABASTECIMIENTO = 15;
    public static final Integer MESES_ABASTECIMIENTO = 1;

    public static final String ERROR_EN_SOLICITUD = "Error en la solicitud: ";
    public static final String RECURSO_NO_ENCONTRADO = "Recurso no encontrado: ";
    public static final String ERROR_INTERNO_SERVIDOR = "Error interno del servidor";
    public static final String ERROR_DESCONOCIDO = "Error desconocido al leer la respuesta";
    public static final String STOCK_SERRVER_ERROR = "El servicio de stock no está disponible, intente más tarde.";
}