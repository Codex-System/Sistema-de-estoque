package com.codexsystem.SistemaDeEstoque.dto.requests;

import java.util.UUID;

public record VendaRequestDTO(
        String codigoBarras,
        Integer quantidade
) {}