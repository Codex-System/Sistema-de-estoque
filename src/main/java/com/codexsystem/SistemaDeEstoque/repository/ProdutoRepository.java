package com.codexsystem.SistemaDeEstoque.repository;

import com.codexsystem.SistemaDeEstoque.domain.Loja;
import com.codexsystem.SistemaDeEstoque.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    List<Produto> findAllByLoja(Loja loja);

    Optional<Produto> findByIdAndLoja(UUID id, Loja loja);

    List<Produto> findByNomeContainingIgnoreCaseAndLoja(String nome, Loja loja);

    Optional<Produto> findByCodigoBarrasAndLoja(String codigoBarras, Loja loja);

}
