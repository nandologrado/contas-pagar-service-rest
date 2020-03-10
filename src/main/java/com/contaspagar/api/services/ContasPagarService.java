package com.contaspagar.api.services;

import com.contaspagar.api.repositories.ContasPagarRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContasPagarService {

    @Autowired
    private ContasPagarRespository contasPagarRespository;


}
