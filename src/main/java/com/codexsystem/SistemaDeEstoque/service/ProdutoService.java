package com.codexsystem.SistemaDeEstoque.service;

import com.codexsystem.SistemaDeEstoque.exceptions.RecursoNaoEncontrado;
import com.codexsystem.SistemaDeEstoque.model.Loja;
import com.codexsystem.SistemaDeEstoque.model.Produto;
import com.codexsystem.SistemaDeEstoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository repo;

    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }

    public List<Produto> getAllByLoja(Loja loja) {
        return repo.findAllByLoja(loja);
    }

    public Produto searchById(UUID id, Loja loja) {
        return repo.findByIdAndLoja(id, loja)
                .orElseThrow(() -> new RecursoNaoEncontrado("Produto não encontrado"));
    }

    public List<Produto> searchByName(String nome, Loja loja) {
        return repo.findByNomeContainingIgnoreCaseAndLoja(nome, loja);
    }

    public Produto save(Produto produto, Loja loja) {
        produto.setLoja(loja);
        return repo.save(produto);
    }

    public void delete(UUID id, Loja loja) {
        Produto produto = repo.findByIdAndLoja(id, loja)
                .orElseThrow(() -> new RecursoNaoEncontrado("Produto não encontrado"));

        repo.delete(produto);
    }

    public Produto baixarEstoque(UUID id, Integer quantidade, Loja loja) {
        Produto produto = searchById(id, loja);

        if (produto.getQuantidade() < quantidade) {
            throw new RuntimeException("Estoque insuficiente");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        return repo.save(produto);
    }

    public Produto buscarPorCodigoBarras(String codigoBarras, Loja loja) {
        return repo.findByCodigoBarrasAndLoja(codigoBarras, loja)
                .orElseThrow(() ->
                        new RecursoNaoEncontrado("Produto não encontrado")
                );
    }

}
