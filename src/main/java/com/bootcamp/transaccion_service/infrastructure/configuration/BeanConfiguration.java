package com.bootcamp.transaccion_service.infrastructure.configuration;

import com.bootcamp.transaccion_service.domain.api.ISuministroServicePort;
import com.bootcamp.transaccion_service.domain.api.IVentaServicePort;
import com.bootcamp.transaccion_service.domain.api.usecase.SuministroUseCase;
import com.bootcamp.transaccion_service.domain.api.usecase.VentaUseCase;
import com.bootcamp.transaccion_service.domain.spi.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IArticuloPersistencePort articuloPersistencePort;
    private final ISuministroPersistencePort suministroPersistencePort;
    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final IVentaPersistencePort ventaPersistencePort;
    private final ICarritoPersistencePort carritoPersistencePort;
    private final IReporteVentaPersistencePort reporteVentaPersistencePort;

    @Bean
    public ISuministroServicePort suministroServicePort() {
        return new SuministroUseCase(suministroPersistencePort, articuloPersistencePort, usuarioPersistencePort);
    }

    @Bean
    public IVentaServicePort ventaServicePort() {
        return new VentaUseCase(carritoPersistencePort, articuloPersistencePort, ventaPersistencePort, usuarioPersistencePort, reporteVentaPersistencePort);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }
}
