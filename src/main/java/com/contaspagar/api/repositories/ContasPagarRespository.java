package com.contaspagar.api.repositories;

import com.contaspagar.api.entities.ContasPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ContasPagarRespository extends JpaRepository<ContasPagar, Long> {
}
