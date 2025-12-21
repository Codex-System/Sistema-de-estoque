package com.codexsystem.SistemaDeEstoque.controller;

import com.codexsystem.SistemaDeEstoque.dto.LoginRequestDTO;
import com.codexsystem.SistemaDeEstoque.dto.UsuarioResponseDTO;
import com.codexsystem.SistemaDeEstoque.model.Usuario;
import com.codexsystem.SistemaDeEstoque.security.JwtUtil;
import com.codexsystem.SistemaDeEstoque.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService userService;

    public AuthController(UsuarioService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@RequestBody LoginRequestDTO data){
        Usuario usuario = userService.registrarUsuario(data.username(), data.password());


        return ResponseEntity.ok(userService.converterParaDTO(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO data){
        Optional<Usuario> usuario = userService.buscarPorUsername(data.username());


        if (usuario.isPresent() && userService.verificarSenha(data.password(), usuario.get().getPassword())) {
            String token = JwtUtil.generateToken(usuario.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Credenciais incorretas");
    }

}
