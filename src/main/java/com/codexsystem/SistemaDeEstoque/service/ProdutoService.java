package com.codexsystem.SistemaDeEstoque.service;

import com.codexsystem.SistemaDeEstoque.exceptions.RecursoNaoEncontrado;
import com.codexsystem.SistemaDeEstoque.model.Produto;
import com.codexsystem.SistemaDeEstoque.repository.ProdutoRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository repo;

    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }

    public List<Produto> getAll() {
        return repo.findAll();
    }

    public Produto searchById(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Produto não encontrado"));
    }

    public List<Produto> searchByName(String nome) {
        return repo.findByNomeContainingIgnoreCase(nome);
    }

    public Produto save(Produto produto) {
        return repo.save(produto);
    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) {
            throw new RecursoNaoEncontrado("Produto com id " + id + " não encontrado");
        }
        repo.deleteById(id);
    }

    public void deleteBynome(String nome){
        repo.deleteByNomeContainingIgnoreCase(nome);
    }
}

