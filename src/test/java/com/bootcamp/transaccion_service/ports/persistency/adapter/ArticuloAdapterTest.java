package com.bootcamp.transaccion_service.ports.persistency.adapter;

import com.bootcamp.transaccion_service.ports.persistency.mysql.adapter.ArticuloAdapter;
import com.bootcamp.transaccion_service.ports.persistency.mysql.repository.IArticuloFeign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class ArticuloAdapterTest {

    @Mock
    private IArticuloFeign articuloFeign;

    @InjectMocks
    private ArticuloAdapter articuloAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSumarExistencia() {
        Long articuloID = 1L;
        Long cantidad = 10L;

        articuloAdapter.sumarExistencia(articuloID, cantidad);

        verify(articuloFeign, times(1)).agregarCantidadArticulo(articuloID, cantidad);
    }
}
