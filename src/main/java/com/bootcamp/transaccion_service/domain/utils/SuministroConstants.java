package com.bootcamp.transaccion_service.domain.utils;

public class SuministroConstants {
    private SuministroConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String ERROR_EN_SOLICITUD = "Error en la solicitud: ";
    public static final String RECURSO_NO_ENCONTRADO = "Recurso no encontrado: ";
    public static final String ERROR_INTERNO_SERVIDOR = "Error interno del servidor";
    public static final String ERROR_DESCONOCIDO = "Error desconocido al leer la respuesta";
}
