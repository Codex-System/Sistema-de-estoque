package com.codexsystem.SistemaDeEstoque.service;

import com.codexsystem.SistemaDeEstoque.dto.UsuarioResponseDTO;
import com.codexsystem.SistemaDeEstoque.model.Usuario;
import com.codexsystem.SistemaDeEstoque.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UserRepository user;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UserRepository user) {
        this.user = user;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Usuario registrarUsuario(String username, String password){
        String senhaCriptografada = passwordEncoder.encode(password);
        Usuario usuario = new Usuario(username, senhaCriptografada);
        return user.save(usuario);
    }
    public boolean verificarSenha(String senhaDigitada, String senhaCriptografada) {
        return passwordEncoder.matches(senhaDigitada, senhaCriptografada);
    }


    public Optional<Usuario> buscarPorUsername(String username){
        return user.findByUsername(username);
    }

    public UsuarioResponseDTO converterParaDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUsername()
        );
    }
}


