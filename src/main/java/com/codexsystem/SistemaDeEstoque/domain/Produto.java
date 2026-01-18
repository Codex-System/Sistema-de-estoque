package com.codexsystem.SistemaDeEstoque.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    private Integer quantidade;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorCompra;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorVenda;

    @CreationTimestamp
    private Instant data;

    @Column(nullable = false)
    private String codigoBarras;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id", nullable = false)
    private Loja loja;

}