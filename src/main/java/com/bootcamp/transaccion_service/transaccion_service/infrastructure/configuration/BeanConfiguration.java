package com.bootcamp.transaccion_service.transaccion_service.infrastructure.configuration;

import com.bootcamp.transaccion_service.transaccion_service.domain.api.ISuministroServicePort;
import com.bootcamp.transaccion_service.transaccion_service.domain.api.usecase.SuministroUseCase;
import com.bootcamp.transaccion_service.transaccion_service.domain.spi.IArticuloPersistencePort;
import com.bootcamp.transaccion_service.transaccion_service.domain.spi.ISuministroPersistencePort;
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

    @Bean
    public ISuministroServicePort suministroServicePort() {
        return new SuministroUseCase(suministroPersistencePort, articuloPersistencePort);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }
}