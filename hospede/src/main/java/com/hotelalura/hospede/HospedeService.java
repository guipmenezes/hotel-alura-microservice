package com.hotelalura.hospede;

import com.hotelalura.hospede.VO.Reservas;
import com.hotelalura.hospede.VO.ResponseTemplate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class HospedeService {
    @Autowired
    private final HospedeRepository hospedeRepository;
    @Autowired
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
                hospede.getHospedeId()
        );
    }

    public ResponseTemplate getHospedeWithReserva(Integer id) {
        ResponseTemplate rt = new ResponseTemplate();
        Hospede hospede = hospedeRepository.findByHospedeId(id);
        Reservas reservas = restTemplate.getForObject("http://RESERVA/reservas/" + hospede.getHospedeId(), Reservas.class);

        rt.setHospede(hospede);
        rt.setReservas(reservas);

        return rt;
    }
}
