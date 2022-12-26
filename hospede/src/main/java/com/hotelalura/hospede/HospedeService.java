package com.hotelalura.hospede;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HospedeService {
    private final HospedeRepository hospedeRepository;
    public void registerHospede(HospedeRegistrationRequest request) {
        Hospede hospede = Hospede.builder()
                .nome(request.nome())
                .sobrenome(request.sobrenome())
                .dataNascimento(request.dataNascimento())
                .nacionalidade(request.nacionalidade())
                .telefone(request.telefone())
                .build();
        //todo: checar validade
        hospedeRepository.save(hospede);
    }
}
