package com.contaspagar.api.service;

import com.contaspagar.api.dtos.ContasPagarDTO;
import com.contaspagar.api.entities.ContasPagar;
import com.contaspagar.api.repositories.ContasPagarRespository;
import com.contaspagar.api.services.ContasPagarService;
import com.contaspagar.api.strategy.multaatraso.MultaAteTresDias;
import com.contaspagar.api.strategy.multaatraso.MultaAtraso;
import com.contaspagar.api.strategy.multaatraso.MultaSuperiorCincoDias;
import com.contaspagar.api.strategy.multaatraso.MultaSuperiorTresDias;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ContasPagarServiceTest {

    @Mock
    private ContasPagarService service;

    @MockBean
    private ContasPagarRespository respository;

    @InjectMocks
    private MultaAteTresDias multaAteTresDias;

    @InjectMocks
    private MultaSuperiorTresDias multaSuperiorTresDias;

    @InjectMocks
    private MultaSuperiorCincoDias multaSuperiorCincoDias;

    @Before
    public void setUp(){
        List<MultaAtraso> litStrategy = new ArrayList<>();
        litStrategy.add(multaSuperiorCincoDias);
        litStrategy.add(multaSuperiorTresDias);
        litStrategy.add(multaAteTresDias);
        service = new ContasPagarService(respository,litStrategy);
    }

    @Test
    public void given_SaveContasPagar_When_SemAtraso_Then_SaveSuccess() {
        ContasPagarDTO contasPagarDTO = new ContasPagarDTO().builder()
                .nome("TESTE")
                .valorOriginal(BigDecimal.valueOf(100))
                .dtPagamento(LocalDate.now())
                .dtVencimento(LocalDate.now())
                .build();

        ContasPagar contasPagar = contasPagarDTO.toEntity();

        when(respository.save(contasPagar)).thenReturn(contasPagar);

        service.salvar(contasPagar);
    }

    @Test
    public void given_SaveContasPagar_When_AteTresDiasAtraso_Then_SaveSuccess() {
        ContasPagarDTO contasPagarDTO = new ContasPagarDTO().builder()
                .nome("TESTE")
                .valorOriginal(BigDecimal.valueOf(100))
                .dtPagamento(LocalDate.now().plusDays(4))
                .dtVencimento(LocalDate.now())
                .build();

        ContasPagar contasPagar = contasPagarDTO.toEntity();

        when(respository.save(contasPagar)).thenReturn(contasPagar);

        service.salvar(contasPagar);
    }

    @Test
    public void given_SaveContasPagar_When_SuperioTresDiasAtraso_Then_SaveSuccess() {
        ContasPagarDTO contasPagarDTO = new ContasPagarDTO().builder()
                .nome("TESTE")
                .valorOriginal(BigDecimal.valueOf(100))
                .dtPagamento(LocalDate.now().plusDays(6))
                .dtVencimento(LocalDate.now())
                .build();

        ContasPagar contasPagar = contasPagarDTO.toEntity();

        when(respository.save(contasPagar)).thenReturn(contasPagar);

        service.salvar(contasPagar);
    }

    @Test
    public void given_SaveContasPagar_When_SuperiorCincoDiasAtraso_Then_SaveSuccess() {
        ContasPagarDTO contasPagarDTO = new ContasPagarDTO().builder()
                .nome("TESTE")
                .valorOriginal(BigDecimal.valueOf(100))
                .dtPagamento(LocalDate.now().plusDays(10))
                .dtVencimento(LocalDate.now())
                .build();

        ContasPagar contasPagar = contasPagarDTO.toEntity();

        when(respository.save(contasPagar)).thenReturn(contasPagar);

        service.salvar(contasPagar);
    }
}
