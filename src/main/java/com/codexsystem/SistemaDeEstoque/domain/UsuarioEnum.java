package com.codexsystem.SistemaDeEstoque.domain;

public enum UsuarioEnum {

    ADMIN("Admin"),
    USER("User");


    String role;

    UsuarioEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
