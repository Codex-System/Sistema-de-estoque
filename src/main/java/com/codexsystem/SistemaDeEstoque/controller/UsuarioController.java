package com.codexsystem.SistemaDeEstoque.controller;

import com.codexsystem.SistemaDeEstoque.dto.CriarUsuarioDTO;
import com.codexsystem.SistemaDeEstoque.dto.UsuarioResponseDTO;
import com.codexsystem.SistemaDeEstoque.model.Loja;
import com.codexsystem.SistemaDeEstoque.model.Usuario;
import com.codexsystem.SistemaDeEstoque.model.UsuarioEnum;
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
