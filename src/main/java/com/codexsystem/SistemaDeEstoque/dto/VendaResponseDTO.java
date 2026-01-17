package com.codexsystem.SistemaDeEstoque.dto;

import java.math.BigDecimal;

public record VendaResponseDTO(
        String produto,
        Integer quantidadeVendida,
        BigDecimal valorUnitario,
        BigDecimal total
) {}
