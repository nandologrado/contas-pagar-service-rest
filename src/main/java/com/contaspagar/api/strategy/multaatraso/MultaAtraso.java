package com.contaspagar.api.strategy.multaatraso;

import java.math.BigDecimal;

public interface MultaAtraso {
    BigDecimal calcularMulta(BigDecimal valor, Long diasAtraso);

    Long getAtrasoMaximo();
}
