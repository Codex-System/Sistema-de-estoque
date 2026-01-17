package com.codexsystem.SistemaDeEstoque.service;

import com.codexsystem.SistemaDeEstoque.domain.Loja;
import com.codexsystem.SistemaDeEstoque.domain.Usuario;
import com.codexsystem.SistemaDeEstoque.repository.LojaRepository;
import org.springframework.stereotype.Service;

@Service
public class LojaService {

    private final LojaRepository lojaRepository;

    public LojaService(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    public Loja criarLoja(String nomeLoja, Usuario admin) {

        lojaRepository.findByLojaAndAdmin(nomeLoja, admin)
                .ifPresent(loja -> {
                    throw new RuntimeException("Você já possui uma loja com esse nome");
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
