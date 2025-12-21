package com.codexsystem.SistemaDeEstoque.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public Usuario(String username, String senhaCriptografada) {
        this.username = username;
        this.password = senhaCriptografada;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername(){
        return username;
    }

    public Long getId() {
        return 0L;
    }
}
