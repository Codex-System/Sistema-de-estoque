package com.codexsystem.SistemaDeEstoque.controller;

import com.codexsystem.SistemaDeEstoque.domain.Loja;
import com.codexsystem.SistemaDeEstoque.domain.Produto;
import com.codexsystem.SistemaDeEstoque.service.ProdutoService;
import com.codexsystem.SistemaDeEstoque.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;

    public ProdutoController(
            ProdutoService produtoService,
            UsuarioService usuarioService
    ) {
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        Loja loja = usuarioService.getLojaDoUsuarioLogado();
        return ResponseEntity.ok(produtoService.getAllByLoja(loja));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable UUID id) {
        Loja loja = usuarioService.getLojaDoUsuarioLogado();
        Produto produto = produtoService.searchById(id, loja);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
        Loja loja = usuarioService.getLojaDoUsuarioLogado();
        return ResponseEntity.ok(produtoService.searchByName(nome, loja));
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto) {
        Loja loja = usuarioService.getLojaDoUsuarioLogado();
        Produto novoProduto = produtoService.save(produto, loja);
        return ResponseEntity.ok(novoProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        Loja loja = usuarioService.getLojaDoUsuarioLogado();
        produtoService.delete(id, loja);
        return ResponseEntity.noContent().build();
    }
}
