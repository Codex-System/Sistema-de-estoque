package com.codexsystem.SistemaDeEstoque.repository;

import com.codexsystem.SistemaDeEstoque.domain.Loja;
import com.codexsystem.SistemaDeEstoque.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LojaRepository extends JpaRepository<Loja, UUID> {

    Optional<Loja> findByAdmin(Usuario admin);

    Optional<Loja> findByLoja(String loja);

    Optional<Loja> findByLojaAndAdmin(String loja, Usuario admin);
}



