package com.bootcamp.transaccion_service.transaccion_service.domain.utils;

public class SuministroValidationMessages {

    private SuministroValidationMessages() {
        throw new AssertionError("Cannot instantiate this class");
    }

    // Mensajes de éxito y error
    public static final String SUMINISTRO_AGREGADO_EXITO = "Suministro agregado exitosamente";
    public static final String SUMINISTRO_NO_ENCONTRADO = "No se encontró el suministro";
    public static final String SUMINISTRO_CREDENCIALES_INCORRECTAS = "Credenciales incorrectas o token inválido";
    public static final String ERROR_INTERNO_SERVIDOR = "Error interno del servidor";

    // Info Swagger
    public static final String AUTH_SUMMARY_AGREGAR_SUMINISTRO = "Agregar nuevo suministro";
    public static final String AUTH_DESCRIPTION_AGREGAR_SUMINISTRO = "Permite agregar un nuevo suministro al sistema.";

    //Utils
    public static final String AUTHORIZATION = "Authorization";
    public static final String AGREGAR_SUMINISTRO_DESCRIPTION = "Agregar suministro";
}