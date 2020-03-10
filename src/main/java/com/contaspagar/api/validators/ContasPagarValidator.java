package com.contaspagar.api.validators;

import com.contaspagar.api.dtos.ContasPagarDTO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import static java.util.Objects.isNull;

public class ContasPagarValidator {
    public static void validarDadosExistentes(ContasPagarDTO contasPagarDTO, BindingResult result) {
        if (isNull(contasPagarDTO)) {
            result.addError(new ObjectError("ContasPagar", "Todas as informações não foram " +
                    "informadas"));
        }

        if (isNull(contasPagarDTO.getNome())) {
            result.addError(new ObjectError("ContasPagar", "O nome não foi informado"));
        }

        if (isNull(contasPagarDTO.getValorOriginal())) {
            result.addError(new ObjectError("ContasPagar", "O valor não foi informado"));
        }

        if (isNull(contasPagarDTO.getDtVencimento())) {
            result.addError(new ObjectError("ContasPagar", "A data de vencimento não foi " +
                    "informada"));
        }

        if (isNull(contasPagarDTO.getDtPagamento())) {
            result.addError(new ObjectError("ContasPagar", "A data de pagamento não foi " +
                    "informada"));
        }
    }
}
