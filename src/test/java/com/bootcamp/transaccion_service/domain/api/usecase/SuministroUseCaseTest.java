package com.bootcamp.transaccion_service.domain.api.usecase;

import com.bootcamp.transaccion_service.domain.model.Suministro;
import com.bootcamp.transaccion_service.domain.spi.IArticuloPersistencePort;
import com.bootcamp.transaccion_service.domain.spi.ISuministroPersistencePort;
import com.bootcamp.transaccion_service.domain.spi.IUsuarioPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SuministroUseCaseTest {

    @Mock
    private ISuministroPersistencePort suministroPersistencePort;

    @Mock
    private IArticuloPersistencePort articuloPersistencePort;

    @Mock
    private IUsuarioPersistencePort usuarioPersistencePort;

    @InjectMocks
    private SuministroUseCase suministroUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void agregarSuministroTest() {
        // Datos de prueba
        Suministro suministro = new Suministro();
        suministro.setArticuloID(1L);
        suministro.setCantidad(10L);

        // Mock de los métodos llamados
        when(usuarioPersistencePort.obtenerUsuarioID()).thenReturn(2L);

        // Acción
        suministroUseCase.agregarSuministro(suministro);

        // Verificar que se haya llamado a sumarExistencia en articuloPersistencePort
        verify(articuloPersistencePort).sumarExistencia(suministro.getArticuloID(), suministro.getCantidad());

        // Verificar que se haya llamado a agregarSuministro en suministroPersistencePort
        ArgumentCaptor<Suministro> suministroCaptor = ArgumentCaptor.forClass(Suministro.class);
        verify(suministroPersistencePort).agregarSuministro(suministroCaptor.capture());

        // Verificaciones adicionales
        Suministro suministroGuardado = suministroCaptor.getValue();
        assertEquals(suministro.getArticuloID(), suministroGuardado.getArticuloID());
        assertEquals(suministro.getCantidad(), suministroGuardado.getCantidad());
        assertEquals(2L, suministroGuardado.getUsuarioID());
        assertEquals(LocalDateTime.now().getDayOfYear(), suministroGuardado.getFechaCreacion().getDayOfYear());
    }
}