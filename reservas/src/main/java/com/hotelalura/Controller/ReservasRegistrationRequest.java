package com.hotelalura.Controller;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public record ReservasRegistrationRequest(
        String roomType,
        @DateTimeFormat(pattern="yyyy-MM-dd")
        Date dataEntrada,
        @DateTimeFormat(pattern="yyyy-MM-dd")
        Date dataSaida,
        Double valor,
        String formaPagamento
) { }
