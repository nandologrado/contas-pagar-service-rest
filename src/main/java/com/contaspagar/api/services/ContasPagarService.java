package com.contaspagar.api.services;

import com.contaspagar.api.entities.ContasPagar;
import com.contaspagar.api.repositories.ContasPagarRespository;
import com.contaspagar.api.strategy.multaatraso.MultaAtraso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ContasPagarService {

    private static final Logger log = LoggerFactory.getLogger(ContasPagarService.class);

    private ContasPagarRespository contasPagarRespository;
    private List<MultaAtraso> multasStrategies;

    @Autowired
    public ContasPagarService(ContasPagarRespository contasPagarRespository, List<MultaAtraso> multasStrategies) {
        this.contasPagarRespository = contasPagarRespository;
        this.multasStrategies = multasStrategies;
    }

    public ContasPagar salvar(ContasPagar contasPagar) {
       log.info("Persistindo conta a pagar: {}", contasPagar);
        if(contasPagar.getDtPagamento().isAfter(contasPagar.getDtVencimento())){
            contasPagar.setQtdDiasAtraso(ChronoUnit.DAYS.between(contasPagar.getDtVencimento(),
                    contasPagar.getDtPagamento()));

            MultaAtraso regraCalculo = multasStrategies.stream()
                    .filter(strategy -> strategy.getAtrasoMinimo() <= contasPagar.getQtdDiasAtraso()
                            && strategy.getAtrasoMaximo() > contasPagar.getQtdDiasAtraso())
                    .findFirst()
                    .get();

            contasPagar.setValorMulta(regraCalculo.calcularMulta(contasPagar.getValorOriginal(), contasPagar.getQtdDiasAtraso()));
            contasPagar.setJuros(regraCalculo.getJurosDia()*contasPagar.getQtdDiasAtraso());
            contasPagar.setMulta(regraCalculo.getMulta());
        }

        return this.contasPagarRespository.save(contasPagar);
    }

    public Page<ContasPagar> buscarContasCadastradas(Pageable pageable) {
        log.info("Listas contas Cadastradas: {}");
        return this.contasPagarRespository.findAll(pageable);
    }



}
