package com.codexsystem.SistemaDeEstoque.controller;

import com.codexsystem.SistemaDeEstoque.dto.CriarLojaDTO;
import com.codexsystem.SistemaDeEstoque.dto.UsuarioResponseDTO;
import com.codexsystem.SistemaDeEstoque.model.Loja;
import com.codexsystem.SistemaDeEstoque.model.Usuario;
import com.codexsystem.SistemaDeEstoque.model.UsuarioEnum;
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

