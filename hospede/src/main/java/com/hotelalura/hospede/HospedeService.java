package com.hotelalura.hospede;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class HospedeService {
    private final HospedeRepository hospedeRepository;
    private final RestTemplate restTemplate;
    public void registerHospede(HospedeRegistrationRequest request) {
        Hospede hospede = Hospede.builder()
                .nome(request.nome())
                .sobrenome(request.sobrenome())
                .dataNascimento(request.dataNascimento())
                .nacionalidade(request.nacionalidade())
                .telefone(request.telefone())
                .build();
        //todo: checar validade
        hospedeRepository.saveAndFlush(hospede);
        //todo: checar se é fraudulento
        FraudCheckResponse fraudCheckResponse= restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{hospedeId}",
                FraudCheckResponse.class,
                hospede.getId()
        );

        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Hospede fraudulento");
        }

        //todo: mandar uma notificação
    }
}
