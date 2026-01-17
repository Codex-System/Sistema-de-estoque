package com.codexsystem.SistemaDeEstoque.model;

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
