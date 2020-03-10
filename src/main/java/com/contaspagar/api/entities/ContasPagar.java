package com.contaspagar.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas_pagar")
public class ContasPagar implements Serializable {
    private static final long serialVersionUID = 1986102946303601488L;

    private Long id;
    private String nome;
    private LocalDate dtVencimento;
    private LocalDate dtPagamento;
    private BigDecimal valorOriginal;



}
