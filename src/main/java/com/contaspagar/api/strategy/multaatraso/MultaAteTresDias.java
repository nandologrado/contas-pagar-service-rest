package com.contaspagar.api.strategy.multaatraso;

import java.math.BigDecimal;

public class MultaAteTresDias implements MultaAtraso{
    private static final double MULTA = 2;
    private static final double JUROS_DIA = 0.1;
    @Override
    public BigDecimal calcularMulta(BigDecimal valor, Long diasAtraso) {
        return (new BigDecimal(MULTA).multiply(valor)).divide(BigDecimal.valueOf(100))
                .add(new BigDecimal(JUROS_DIA*diasAtraso).multiply(valor)).divide(BigDecimal.valueOf(100));
    }

    @Override
    public Long getAtrasoMaximo() {
        return 3L;
    }


}
