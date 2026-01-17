package com.codexsystem.SistemaDeEstoque.dto.responses;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoPDVResponseDTO(
        UUID id,
        String nome,
        BigDecimal valorVenda,
        Integer quantidade
) {}
