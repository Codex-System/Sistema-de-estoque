package com.codexsystem.SistemaDeEstoque.dto;

import java.util.UUID;

public record VendaRequestDTO(
        UUID produtoId,
        Integer quantidade
) {}
