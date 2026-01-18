package com.codexsystem.SistemaDeEstoque.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.codexsystem.SistemaDeEstoque.domain.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret:meu-segredo-super-seguro-de-pelo-menos-32-caracteres}")
    private String secret;


    public String gerarAccessToken(Usuario usuario) {
        return criarToken(usuario, 15);
    }


    public String gerarRefreshToken(Usuario usuario) {
        return criarToken(usuario, 1440);
    }

    private String criarToken(Usuario usuario, int minutos) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("sistema-estoque")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(Instant.now().plus(Duration.ofMinutes(minutos)))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }

    /**
     * Valida o token e retorna o username (subject) se estiver tudo ok
     */
    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("sistema-estoque")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            // Retorna null para que o SecurityFilter saiba que o token não é válido
            return null;
        }
    }
}