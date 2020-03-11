package com.contaspagar.api.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString()
@Table(name = "contas_pagar")
public class ContasPagar implements Serializable {
    private static final long serialVersionUID = 1986102946303601488L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dt_vencimento", nullable = false)
    private LocalDate dtVencimento;

    @Column(name = "dt_pagamento", nullable = false)
    private LocalDate dtPagamento;

    @Column(name = "valor_original", nullable = false)
    private BigDecimal valorOriginal;

    @Column(name = "valor_multa", nullable = false)
    private BigDecimal valorMulta;

    @Column(name = "dias_atraso", nullable = false)
    private Long qtdDiasAtraso;

    @Column(name = "multa", nullable = false)
    private double multa;

    @Column(name = "juros", nullable = false)
    private double juros;

}
