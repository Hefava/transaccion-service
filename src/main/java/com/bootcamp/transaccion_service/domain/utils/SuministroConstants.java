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
    public static final String EL_ARTICULO = "El artículo ";
    public static final String NO_IENE_STOCK = " no tiene stock disponible";
    public static final String VENTA_REALIZADA = "Venta realizada con éxito";
    public static final String ERROR_CANTIDAD = "La cantidad debe ser mayor que 0.";
    public static final long MIN_CANTIDAD = 1L;

    public static final String ERROR_VENTA = "Error al realizar la venta";
    public static final String SORT_BY_NAME = "nombre";
    public static final String ORDER_ASC = "asc";
    public static final Long DEFAULT_ARTICULO_ID = 1L;
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 10000;
}