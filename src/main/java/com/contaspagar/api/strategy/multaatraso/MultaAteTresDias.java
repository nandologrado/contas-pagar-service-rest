package com.contaspagar.api.strategy.multaatraso;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MultaAteTresDias implements MultaAtraso{
    private static final double MULTA = 2;
    private static final double JUROS_DIA = 0.1;

    @Override
    public BigDecimal calcularMulta(BigDecimal valor, Long diasAtraso) {
        return (new BigDecimal(MULTA).multiply(valor)).divide(BigDecimal.valueOf(100))
                .add((new BigDecimal(JUROS_DIA*diasAtraso).multiply(valor)).divide(BigDecimal.valueOf(100)));
    }

    @Override
    public Integer getAtrasoMaximo() {
        return 3;
    }

    @Override
    public Integer getAtrasoMinimo() {
        return 0;
    }

    @Override
    public double getMulta() {
        return MULTA;
    }

    @Override
    public double getJurosDia() {
        return JUROS_DIA;
    }
}
