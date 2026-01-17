package com.codexsystem.SistemaDeEstoque.service;

import com.codexsystem.SistemaDeEstoque.dto.UsuarioResponseDTO;
import com.codexsystem.SistemaDeEstoque.model.Loja;
import com.codexsystem.SistemaDeEstoque.model.Usuario;
import com.codexsystem.SistemaDeEstoque.model.UsuarioEnum;
import com.codexsystem.SistemaDeEstoque.repository.LojaRepository;
import com.codexsystem.SistemaDeEstoque.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LojaRepository lojaRepository;

    public UsuarioService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            LojaRepository lojaRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.lojaRepository = lojaRepository;
    }

    public Usuario criarAdmin(String username, String password) {
        return criarUsuario(username, password, UsuarioEnum.ADMIN, null);
    }

    public Usuario criarUser(String username, String password, Loja loja) {
        return criarUsuario(username, password, UsuarioEnum.USER, loja);
    }

    private Usuario criarUsuario(
            String username,
            String password,
            UsuarioEnum role,
            Loja loja
    ) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username já existe");
        }

        Usuario usuario = new Usuario(
                null,
                username,
                passwordEncoder.encode(password),
                role,
                loja
        );

        return userRepository.save(usuario);
    }

    public Loja getLojaDoUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();

        if (usuario.getRole() == UsuarioEnum.ADMIN) {
            return lojaRepository.findByAdmin(usuario)
                    .orElseThrow(() -> new RuntimeException("Admin sem loja"));
        }

        return usuario.getLoja();
    }

    public UsuarioResponseDTO toDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUsername()
        );
    }
}


