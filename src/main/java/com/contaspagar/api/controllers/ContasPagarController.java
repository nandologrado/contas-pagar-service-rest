package com.contaspagar.api.controllers;

import com.contaspagar.api.dtos.ContasPagarDTO;
import com.contaspagar.api.entities.ContasPagar;
import com.contaspagar.api.response.Response;
import com.contaspagar.api.services.ContasPagarService;
import com.contaspagar.api.validators.ContasPagarValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/contas-pagar")
public class ContasPagarController {

    private static final Logger log = LoggerFactory.getLogger(ContasPagarController.class);

    @Autowired
    ContasPagarService contasPagarService;

    private int qtdPorPagina = 20;

    public ContasPagarController() {
    }

    /**
     * Cadastra contas a pagar.
     * @param contasPagarDTO
     * @return ResponseEntity<Response<ContasPagarDTO>>
     */
    @PostMapping
    public ResponseEntity<Response<ContasPagarDTO>> cadastrar(@Valid @RequestBody ContasPagarDTO contasPagarDTO,
                                                              BindingResult result) {
        log.info("Cadastrando Conta a pagar: {}", contasPagarDTO.toString());
        Response<ContasPagarDTO> response = new Response<>();
        ContasPagarValidator.validarDadosExistentes(contasPagarDTO, result);
        ContasPagar contasPagar = contasPagarDTO.toEntity();

        if (result.hasErrors()) {
            log.error("Erro validando dados de cadastro Contas a Pagar: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        this.contasPagarService.salvar(contasPagar);

        response.setData(ContasPagarDTO.valueOf(contasPagar));
        return ResponseEntity.ok(response);

    }

    /**
     * Retorna a listagem de contas cadastradas.
     *
     * @return ResponseEntity<Response<ContasPagarDTO>>
     */
    @GetMapping(value = "/listar-contas-cadastradas")
    public ResponseEntity<Response<Page<ContasPagarDTO>>> listarContasCadastradas(
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir) {
        log.info("Buscando contas cadastradas: {}, página: {}",  pag);
        Response<Page<ContasPagarDTO>> response = new Response<>();

        Pageable pageable = PageRequest.of(pag, this.qtdPorPagina, Sort.Direction.valueOf(dir));

        Page<ContasPagar> contas = this.contasPagarService.buscarContasCadastradas(pageable);
        Page<ContasPagarDTO> contasDto = contas.map(ContasPagarDTO::valueOf);

        response.setData(contasDto);
        return ResponseEntity.ok(response);
    }
}
