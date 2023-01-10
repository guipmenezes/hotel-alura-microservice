package com.hotelalura;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public record ReservasRegistrationRequest(
        @DateTimeFormat(pattern="yyyy-MM-dd")
        Date dataEntrada,
        @DateTimeFormat(pattern="yyyy-MM-dd")
        Date dataSaida,
        Integer valor,
        String formaPagamento
) { }
