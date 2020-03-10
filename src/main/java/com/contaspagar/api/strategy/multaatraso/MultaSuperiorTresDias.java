package com.contaspagar.api.strategy.multaatraso;

import java.math.BigDecimal;

public class MultaSuperiorTresDias implements MultaAtraso{
    private static final double MULTA = 3;
    private static final double JUROS_DIA = 0.2;
    @Override
    public BigDecimal calcularMulta(BigDecimal valor, Long diasAtraso) {
        return (new BigDecimal(MULTA).multiply(valor)).divide(BigDecimal.valueOf(100))
                .add(new BigDecimal(JUROS_DIA*diasAtraso).multiply(valor)).divide(BigDecimal.valueOf(100));
    }

    @Override
    public Long getAtrasoMaximo() {
        return 5L;
    }
}
