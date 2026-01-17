package com.codexsystem.SistemaDeEstoque.controller;

import com.codexsystem.SistemaDeEstoque.dto.requests.CriarUsuarioDTO;
import com.codexsystem.SistemaDeEstoque.dto.responses.UsuarioResponseDTO;
import com.codexsystem.SistemaDeEstoque.domain.Loja;
import com.codexsystem.SistemaDeEstoque.domain.Usuario;
import com.codexsystem.SistemaDeEstoque.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody CriarUsuarioDTO dto) {

        Loja loja = usuarioService.getLojaDoUsuarioLogado();

        Usuario usuario = usuarioService.criarUser(
                dto.username(),
                dto.password(),
                loja
        );


        return ResponseEntity.ok(usuarioService.toDTO(usuario));
    }
}
