package com.codexsystem.SistemaDeEstoque.controller;

import com.codexsystem.SistemaDeEstoque.domain.Usuario;
import com.codexsystem.SistemaDeEstoque.dto.requests.LoginRequestDTO;
import com.codexsystem.SistemaDeEstoque.dto.requests.RefreshRequestDTO;
import com.codexsystem.SistemaDeEstoque.dto.responses.LoginResponseDTO;
import com.codexsystem.SistemaDeEstoque.repository.UserRepository;
import com.codexsystem.SistemaDeEstoque.service.TokenService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authManager, TokenService tokenService, UserRepository userRepository) {
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {

        var authToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());

        var auth = authManager.authenticate(authToken);


        var usuario = (Usuario) auth.getPrincipal();


        String accessToken = tokenService.gerarAccessToken(usuario);
        String refreshToken = tokenService.gerarRefreshToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(@RequestBody RefreshRequestDTO dto) {

        String username = tokenService.validarToken(dto.refreshToken());

        if (username == null) {

            return ResponseEntity.status(403).build();
        }


        Usuario usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        String newAccessToken = tokenService.gerarAccessToken(usuario);
        String newRefreshToken = tokenService.gerarRefreshToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(newAccessToken, newRefreshToken));
    }
}