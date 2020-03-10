package com.contaspagar.api.controllers;

import com.contaspagar.api.services.ContasPagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contas-pagar")
public class ContasPagarController {

    @Autowired
    ContasPagarService contasPagarService;

    public ContasPagarController() {
    }


}
