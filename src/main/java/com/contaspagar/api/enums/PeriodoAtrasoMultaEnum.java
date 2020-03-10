package com.contaspagar.api.enums;

import com.contaspagar.api.strategy.multaatraso.MultaAtraso;
import com.contaspagar.api.strategy.multaatraso.MultaSuperiorCincoDias;
import com.contaspagar.api.strategy.multaatraso.MultaSuperiorTresDias;
import com.contaspagar.api.strategy.multaatraso.MultaAteTresDias;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PeriodoAtrasoMultaEnum {
    SUPERIOR_CINCO_DIAS(5, new MultaSuperiorCincoDias()),
    SUPERIOR_TRES_DIAS(3, new MultaSuperiorTresDias()),
    ATE_TRES_DIAS(0, new MultaAteTresDias());

    private final Integer periodoMinimo;
    private MultaAtraso multaAtraso;

}
