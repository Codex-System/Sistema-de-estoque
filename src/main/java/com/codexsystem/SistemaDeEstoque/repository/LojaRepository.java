package com.codexsystem.SistemaDeEstoque.repository;

import com.codexsystem.SistemaDeEstoque.model.Loja;
import com.codexsystem.SistemaDeEstoque.model.Produto;
import com.codexsystem.SistemaDeEstoque.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LojaRepository extends JpaRepository<Loja, UUID> {

    Optional<Loja> findByAdmin(Usuario admin);

    Optional<Loja> findByLoja(String loja);

    Optional<Loja> findByLojaAndAdmin(String loja, Usuario admin);
}



