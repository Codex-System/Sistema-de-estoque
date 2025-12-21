package com.codexsystem.SistemaDeEstoque.controller;

import com.codexsystem.SistemaDeEstoque.model.Produto;
import com.codexsystem.SistemaDeEstoque.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        Produto produto = produtoService.searchById(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
        List<Produto> produtos = produtoService.searchByName(nome);
        return ResponseEntity.ok(produtos);
    }


    @PostMapping("adiciona")
    public ResponseEntity<Produto> create(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.save(produto);
        return ResponseEntity.ok(novoProduto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.ok("Produto deletado com sucesso");
    }

    @DeleteMapping("/delete/{nome}")
    public ResponseEntity<String> deleteByname(@PathVariable String nome){
        produtoService.deleteBynome(nome);
        return ResponseEntity.ok("Produto deleta com sucesso");
    }
}
