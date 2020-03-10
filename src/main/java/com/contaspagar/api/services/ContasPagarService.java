package com.contaspagar.api.services;

import com.contaspagar.api.entities.ContasPagar;
import com.contaspagar.api.enums.PeriodoAtrasoMultaEnum;
import com.contaspagar.api.repositories.ContasPagarRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class ContasPagarService {

    private static final Logger log = LoggerFactory.getLogger(ContasPagarService.class);

    @Autowired
    private ContasPagarRespository contasPagarRespository;

    public ContasPagar salvar(ContasPagar contasPagar) {
        log.info("Persistindo conta a pagar: {}", contasPagar);
        if(contasPagar.getDtPagamento().isAfter(contasPagar.getDtVencimento())){
            contasPagar.setQtdDiasAtraso(ChronoUnit.DAYS.between(contasPagar.getDtPagamento(),
                    contasPagar.getDtVencimento()));
        }

        return this.contasPagarRespository.save(contasPagar);
    }

    public Page<ContasPagar> buscarContasCadastradas(Pageable pageable) {
        log.info("Listas contas Cadastradas: {}");
        return this.contasPagarRespository.findAll(pageable);
    }


}
