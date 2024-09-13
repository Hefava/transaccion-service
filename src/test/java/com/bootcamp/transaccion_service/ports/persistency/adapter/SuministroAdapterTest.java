package com.bootcamp.transaccion_service.ports.persistency.adapter;

import com.bootcamp.transaccion_service.domain.model.Suministro;
import com.bootcamp.transaccion_service.ports.persistency.mysql.adapter.SuministroAdapter;
import com.bootcamp.transaccion_service.ports.persistency.mysql.entity.SuministroEntity;
import com.bootcamp.transaccion_service.ports.persistency.mysql.mapper.SuministroEntityMapper;
import com.bootcamp.transaccion_service.ports.persistency.mysql.repository.ISuministroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class SuministroAdapterTest {

    @Mock
    private ISuministroRepository suministroRepository;

    @Mock
    private SuministroEntityMapper suministroEntityMapper;

    @InjectMocks
    private SuministroAdapter suministroAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void agregarSuministroTest() {
        // Datos de prueba
        Suministro suministro = new Suministro();
        SuministroEntity suministroEntity = new SuministroEntity();

        // Mockear el mapeo de Suministro a SuministroEntity
        when(suministroEntityMapper.toEntity(suministro)).thenReturn(suministroEntity);

        // Ejecutar el método
        suministroAdapter.agregarSuministro(suministro);

        // Verificar que se haya llamado al mapper para convertir Suministro a SuministroEntity
        verify(suministroEntityMapper, times(1)).toEntity(suministro);

        // Verificar que se haya llamado al repositorio para guardar la entidad
        verify(suministroRepository, times(1)).save(suministroEntity);
    }
}