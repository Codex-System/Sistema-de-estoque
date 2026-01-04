package com.codexsystem.SistemaDeEstoque.repository;

import com.codexsystem.SistemaDeEstoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    Produto deleteByNomeContainingIgnoreCase(String nome);

}

