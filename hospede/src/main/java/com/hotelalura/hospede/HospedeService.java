package com.hotelalura.hospede;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HospedeService {
    private final HospedeRepository hospedeRepository;
    private final RestTemplate restTemplate;

    public List<Hospede> findHospede() {
        return hospedeRepository.findAll();
    }

    public void deleteHospede(Integer id) {
        hospedeRepository.deleteById(id);
    }

    public void registerHospede(HospedeRegistrationRequest request) {
        Hospede hospede = Hospede.builder()
                .nome(request.nome())
                .sobrenome(request.sobrenome())
                .dataNascimento(request.dataNascimento())
                .nacionalidade(request.nacionalidade())
                .telefone(request.telefone())
                .build();

        hospedeRepository.saveAndFlush(hospede);

        FraudeCheckResponse fraudCheckResponse= restTemplate.getForObject(
                "http://FRAUDE/fraude-check/{hospedeId}",
                FraudeCheckResponse.class,
                hospede.getId()
        );

        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Hospede fraudulento");
        }

        //todo: mandar uma notificação
    }
}
