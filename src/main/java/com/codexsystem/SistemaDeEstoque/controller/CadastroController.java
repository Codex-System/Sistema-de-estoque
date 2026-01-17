package com.codexsystem.SistemaDeEstoque.controller;

import com.codexsystem.SistemaDeEstoque.dto.requests.CriarLojaDTO;
import com.codexsystem.SistemaDeEstoque.dto.responses.UsuarioResponseDTO;
import com.codexsystem.SistemaDeEstoque.domain.Usuario;
import com.codexsystem.SistemaDeEstoque.service.LojaService;
import com.codexsystem.SistemaDeEstoque.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    private final LojaService lojaService;
    private final UsuarioService usuarioService;

    public CadastroController(LojaService lojaService,
                              UsuarioService usuarioService) {
        this.lojaService = lojaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/loja")
    public ResponseEntity<UsuarioResponseDTO> criarLoja(@RequestBody CriarLojaDTO dto) {

        // 1️⃣ cria o admin
        Usuario admin = usuarioService.criarAdmin(
                dto.username(),
                dto.password()
        );

        return ResponseEntity.ok(usuarioService.toDTO(admin));
    }
}

