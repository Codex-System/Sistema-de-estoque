package com.codexsystem.SistemaDeEstoque.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private Integer quantidade;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorCompra;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorVenda;

    private LocalDate data = LocalDate.now();
}