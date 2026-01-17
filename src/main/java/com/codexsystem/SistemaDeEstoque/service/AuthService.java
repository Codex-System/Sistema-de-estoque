package com.codexsystem.SistemaDeEstoque.service;

import com.codexsystem.SistemaDeEstoque.dto.requests.LoginRequestDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void login(LoginRequestDTO dto) {
        var authToken =
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password());

        authenticationManager.authenticate(authToken);
    }
}
