package com.codexsystem.SistemaDeEstoque.dto.responses;

import java.math.BigDecimal;

public record VendaResponseDTO(
        String produto,
        Integer quantidadeVendida,
        BigDecimal valorUnitario,
        BigDecimal total
) {}
