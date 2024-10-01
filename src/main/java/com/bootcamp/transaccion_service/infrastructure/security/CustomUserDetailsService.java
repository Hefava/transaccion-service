package com.bootcamp.transaccion_service.infrastructure.security;

import com.bootcamp.transaccion_service.ports.application.http.dto.UsuarioResponse;
import com.bootcamp.transaccion_service.ports.feign.UsuarioFeign;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.bootcamp.transaccion_service.domain.utils.SecurityConstants.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioFeign usuarioClient;

    public CustomUserDetailsService(UsuarioFeign usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        UsuarioResponse usuarioResponse = usuarioClient.validateToken(token);

        if (usuarioResponse == null || usuarioResponse.getUsername() == null) {
            throw new UsernameNotFoundException(TOKEN_INVALIDO);
        }

        return new org.springframework.security.core.userdetails.User(
                usuarioResponse.getUsername(),
                WHITE_SPACE,
                Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + usuarioResponse.getRole()))
        );
    }
}