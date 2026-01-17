package com.codexsystem.SistemaDeEstoque.service;

import com.codexsystem.SistemaDeEstoque.dto.requests.VendaRequestDTO;
import com.codexsystem.SistemaDeEstoque.dto.responses.VendaResponseDTO;
import com.codexsystem.SistemaDeEstoque.exceptions.RecursoNaoEncontrado;
import com.codexsystem.SistemaDeEstoque.domain.Loja;
import com.codexsystem.SistemaDeEstoque.domain.Produto;
import com.codexsystem.SistemaDeEstoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VendaService {

    private final ProdutoRepository produtoRepository;

    public VendaService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public VendaResponseDTO realizarVenda(
            VendaRequestDTO dto,
            Loja loja
    ) {
        Produto produto = produtoRepository
                .findByIdAndLoja(dto.produtoId(), loja)
                .orElseThrow(() ->
                        new RecursoNaoEncontrado("Produto não encontrado")
                );

        if (produto.getQuantidade() <= 0) {
            throw new RuntimeException("Produto sem estoque");
        }

        if (dto.quantidade() > produto.getQuantidade()) {
            throw new RuntimeException("Quantidade maior que o estoque disponível");
        }

        BigDecimal total =
                produto.getValorVenda()
                        .multiply(BigDecimal.valueOf(dto.quantidade()));

        produto.setQuantidade(produto.getQuantidade() - dto.quantidade());

        if (produto.getQuantidade() == 0) {
            produtoRepository.delete(produto);
        } else {
            produtoRepository.save(produto);
        }

        return new VendaResponseDTO(
                produto.getNome(),
                dto.quantidade(),
                produto.getValorVenda(),
                total
        );
    }
}
