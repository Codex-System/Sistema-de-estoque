package com.codexsystem.SistemaDeEstoque.controller;

import com.codexsystem.SistemaDeEstoque.domain.Loja;
import com.codexsystem.SistemaDeEstoque.domain.Produto;
import com.codexsystem.SistemaDeEstoque.dto.requests.VendaRequestDTO;
import com.codexsystem.SistemaDeEstoque.dto.responses.ProdutoPDVResponseDTO;
import com.codexsystem.SistemaDeEstoque.dto.responses.VendaResponseDTO;
import com.codexsystem.SistemaDeEstoque.service.ProdutoService;
import com.codexsystem.SistemaDeEstoque.service.UsuarioService;
import com.codexsystem.SistemaDeEstoque.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdv")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class PDVController {

    private final ProdutoService produtoService;
    private final VendaService vendaService;
    private final UsuarioService usuarioService;

    public PDVController(
            ProdutoService produtoService,
            VendaService vendaService,
            UsuarioService usuarioService
    ) {
        this.produtoService = produtoService;
        this.vendaService = vendaService;
        this.usuarioService = usuarioService;
    }

    // 🔎 Leitura do código de barras
    @GetMapping("/produto/{codigoBarras}")
    public ResponseEntity<ProdutoPDVResponseDTO> buscarProduto(
            @PathVariable String codigoBarras
    ) {
        Loja loja = usuarioService.getLojaDoUsuarioLogado();

        Produto produto =
                produtoService.buscarPorCodigoBarras(codigoBarras, loja);

        if (produto.getQuantidade() <= 0) {
            throw new RuntimeException("Produto sem estoque");
        }

        return ResponseEntity.ok(
                new ProdutoPDVResponseDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getValorVenda(),
                        produto.getQuantidade()
                )
        );
    }

    @PostMapping("/venda")
    public ResponseEntity<VendaResponseDTO> vender(
            @RequestBody VendaRequestDTO dto
    ) {
        Loja loja = usuarioService.getLojaDoUsuarioLogado();
        return ResponseEntity.ok(
                vendaService.realizarVenda(dto, loja)
        );
    }
}
