package com.codexsystem.SistemaDeEstoque.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoPDVResponseDTO(
        UUID id,
        String nome,
        BigDecimal valorVenda,
        Integer quantidade
) {}
