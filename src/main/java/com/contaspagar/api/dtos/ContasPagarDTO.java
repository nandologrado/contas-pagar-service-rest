package com.contaspagar.api.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContasPagarDTO {
    private String nome;
    private BigDecimal valorOriginal;
    private BigDecimal valorCorrigido;
    private LocalDate dtPagamento;
    private LocalDate dtVencimento;
    private Integer qtdDiasAtraso;
}
