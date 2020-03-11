DROP DATABASE IF EXISTS contas_pagar;
CREATE DATABASE contas_pagar;
USE contas_pagar;

CREATE TABLE contas_pagar (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dt_pagamento date NOT NULL,
  dt_vencimento date NOT NULL,
  juros double NOT NULL,
  multa double NOT NULL,
  nome varchar(255) NOT NULL,
  dias_atraso bigint(20) NOT NULL,
  valor_multa decimal(19,2) NOT NULL,
  valor_original decimal(19,2) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

mvn -Dflyway.user=root -Dflyway.password=223671ff* -Dflyway.schemas=contas_pagar