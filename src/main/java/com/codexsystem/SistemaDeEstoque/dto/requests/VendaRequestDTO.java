package com.codexsystem.SistemaDeEstoque.dto.requests;

import java.util.UUID;

public record VendaRequestDTO(
        UUID produtoId,
        Integer quantidade
) {}
