package com.bootcamp.transaccion_service.ports.application.http.controller;

import com.bootcamp.transaccion_service.domain.api.ISuministroServicePort;
import com.bootcamp.transaccion_service.domain.model.Suministro;
import com.bootcamp.transaccion_service.ports.application.http.dto.AgregarSuministroRequest;
import com.bootcamp.transaccion_service.ports.application.http.mapper.AgregarSuministroRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class SuministroRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ISuministroServicePort suministroServicePort;

    @Mock
    private AgregarSuministroRequestMapper agregarSuministroRequestMapper;

    @InjectMocks
    private SuministroRestController suministroRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(suministroRestController).build();
    }

    @Test
    void testAgregarSuministro() throws Exception {
        // Simulación de objetos de entrada
        AgregarSuministroRequest request = new AgregarSuministroRequest();
        request.setArticuloID(1L);
        request.setCantidad(10L);

        Suministro suministro = new Suministro();
        suministro.setArticuloID(1L);
        suministro.setCantidad(10L);

        // Mockear el mapeo de request a domain model
        when(agregarSuministroRequestMapper.toSuministro(any(AgregarSuministroRequest.class))).thenReturn(suministro);

        // Mockear la ejecución del servicio
        doNothing().when(suministroServicePort).agregarSuministro(any(Suministro.class));

        // Realizar la llamada HTTP simulada y validar el resultado
        mockMvc.perform(post("/suministro/agregar-suministro")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"articuloID\": 1, \"cantidad\": 10}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(""));

        // Verificar que el mapper y el servicio fueron invocados correctamente
        verify(agregarSuministroRequestMapper).toSuministro(any(AgregarSuministroRequest.class));
        verify(suministroServicePort).agregarSuministro(any(Suministro.class));
    }
}