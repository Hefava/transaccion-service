package com.bootcamp.transaccion_service.domain.utils;

public class SecurityConstants {
    public static final String ROL_ADMIN = "ADMIN";
    public static final String ROL_AUX_BODEGA = "AUX_BODEGA";
    public static final String ROL_CLIENTE = "CLIENTE";
    public static final String ROLE_PREFIX = "ROLE_";

    public static final String ACCESO_DENEGADO = "Acceso denegado: No tienes permiso para acceder a este recurso.";
    public static final String WHITE_SPACE = "";

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String TOKEN_INVALIDO = "Token inválido o usuario no encontrado";

    private SecurityConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
