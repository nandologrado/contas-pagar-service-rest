package com.contaspagar.api.strategy.multaatraso;

import java.math.BigDecimal;

public interface MultaAtraso {
    BigDecimal calcularMulta(BigDecimal valor, Long diasAtraso);

    Integer getAtrasoMinimo();

    Integer getAtrasoMaximo();

    double getMulta();

    double getJurosDia();
}
