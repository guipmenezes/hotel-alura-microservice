package com.hotelalura.hospede.Controller;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public record HospedeRegistrationRequest(
        String nome,
        String sobrenome,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date dataNascimento,
        String nacionalidade,
        String telefone) {
}
