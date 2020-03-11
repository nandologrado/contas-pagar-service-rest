package com.contaspagar.api.dtos;

import com.contaspagar.api.entities.ContasPagar;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import static java.util.Objects.isNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonIgnoreProperties
public class ContasPagarDTO implements Serializable {
    private static final long serialVersionUID = 5735596859156272751L;

    private String nome;
    private BigDecimal valorOriginal;
    private BigDecimal valorMulta;
    private LocalDate dtPagamento;
    private LocalDate dtVencimento;
    private Long qtdDiasAtraso;
    private Double juros;
    private Double multa;

   public ContasPagar toEntity(){
        return ContasPagar.builder()
                .nome(this.nome)
                .dtPagamento(this.dtPagamento)
                .dtVencimento(this.dtVencimento)
                .valorOriginal(this.valorOriginal)
                .valorMulta(isNull(this.valorMulta) ? BigDecimal.valueOf(0.0) : this.valorMulta)
                .qtdDiasAtraso(isNull(this.qtdDiasAtraso) ? 0 : this.qtdDiasAtraso)
                .juros(isNull(this.juros) ? 0 : this.juros)
                .multa(isNull(this.multa) ? 0 : this.multa)
                .build();
    }

    public static ContasPagarDTO valueOf(ContasPagar contasPagar) {
        if(isNull(contasPagar)){
            return ContasPagarDTO.builder().build();
        }

        return ContasPagarDTO.builder()
                .nome(contasPagar.getNome())
                .valorOriginal(contasPagar.getValorOriginal())
                .valorMulta(BigDecimal.valueOf(0.0))
                .qtdDiasAtraso(contasPagar.getQtdDiasAtraso())
                .dtPagamento(contasPagar.getDtPagamento())
                .build();
    }

}
