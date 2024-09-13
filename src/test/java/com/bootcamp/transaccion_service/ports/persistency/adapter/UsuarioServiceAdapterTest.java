package com.bootcamp.transaccion_service.ports.persistency.adapter;

import com.bootcamp.transaccion_service.ports.persistency.mysql.adapter.UsuarioServiceAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UsuarioServiceAdapterTest {

    private UsuarioServiceAdapter usuarioServiceAdapter;

    @BeforeEach
    void setUp() {
        usuarioServiceAdapter = new UsuarioServiceAdapter();
    }

    @Test
    void testObtenerUsuarioID() {
        // Mockear el contexto de seguridad
        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {

            // Crear mocks para Authentication y SecurityContext
            SecurityContext mockSecurityContext = mock(SecurityContext.class);
            Authentication mockAuthentication = mock(Authentication.class);
            UserDetails mockUserDetails = mock(UserDetails.class);

            // Configurar el mock de SecurityContextHolder para devolver el contexto de seguridad simulado
            mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(mockSecurityContext);

            // Configurar el mock del contexto de seguridad para devolver la autenticación simulada
            when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);

            // Configurar el mock de la autenticación para devolver los detalles del usuario
            when(mockAuthentication.getPrincipal()).thenReturn(mockUserDetails);

            // Simular que el usuario tiene el nombre de usuario "123"
            when(mockUserDetails.getUsername()).thenReturn("123");

            // Ejecutar el método que se va a probar
            Long usuarioID = usuarioServiceAdapter.obtenerUsuarioID();

            // Verificar que el resultado sea el esperado
            assertEquals(123L, usuarioID);
        }
    }
}
