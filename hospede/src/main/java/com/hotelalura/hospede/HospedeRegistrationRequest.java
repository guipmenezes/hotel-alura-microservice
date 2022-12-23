package com.hotelalura.hospede;

import java.sql.Date;

public record HospedeRegistrationRequest(
        String nome,
        String sobrenome,
        Date dataNascimento,
        String nacionalidade,
        String telefone) {
}
