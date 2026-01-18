package com.codexsystem.SistemaDeEstoque.service;

import com.codexsystem.SistemaDeEstoque.domain.Loja;
import com.codexsystem.SistemaDeEstoque.domain.Usuario;
import com.codexsystem.SistemaDeEstoque.repository.LojaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LojaService {

    private final LojaRepository lojaRepository;

    public LojaService(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    @Transactional
    public Loja criarLoja(String nomeLoja, Usuario admin) {
        // Verifica se já existe uma loja com esse nome (Regra de negócio)
        lojaRepository.findByLoja(nomeLoja).ifPresent(l -> {
            throw new RuntimeException("Este nome de loja já está em uso");
        });

        Loja loja = new Loja();
        loja.setLoja(nomeLoja);
        loja.setAdmin(admin);

        return lojaRepository.save(loja);
    }

    public Loja buscarPorAdmin(Usuario admin) {
        return lojaRepository.findByAdmin(admin)
                .orElseThrow(() ->
                        new RuntimeException("Admin não possui loja")
                );
    }
}
