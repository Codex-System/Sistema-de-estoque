package com.codexsystem.SistemaDeEstoque.repository;

import com.codexsystem.SistemaDeEstoque.model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByUsername(String username);
}
