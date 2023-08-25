package com.hotelalura.hospede.Service;

import com.hotelalura.hospede.Controller.HospedeRegistrationRequest;
import com.hotelalura.hospede.Model.Hospede;
import com.hotelalura.hospede.Model.HospedeRepository;
import com.hotelalura.hospede.VO.Reservas;
import com.hotelalura.hospede.VO.ResponseTemplate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class HospedeService {

    private final HospedeRepository hospedeRepository;
    private final RestTemplate restTemplate;

    public List<Hospede> findHospede() {
        return hospedeRepository.findAll();
    }

    public ResponseEntity<Void> deleteHospede(Integer id) {
        return hospedeRepository.findById(id)
                .map(hospede -> {
                    hospedeRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    public ResponseEntity<Hospede> registerHospede(HospedeRegistrationRequest request) {
        Hospede hospede = Hospede.builder()
                .nome(request.nome())
                .sobrenome(request.sobrenome())
                .dataNascimento(request.dataNascimento())
                .nacionalidade(request.nacionalidade())
                .telefone(request.telefone())
                .build();

        // Retirando o check de fraude, ele está nulo
//        FraudeCheckResponse fraudCheckResponse= restTemplate.getForObject(
//        "http://FRAUDE/fraude-check/{hospedeId}",
//            FraudeCheckResponse.class,
//            hospede.getHospedeId()
//        );

        return ResponseEntity.status(HttpStatus.CREATED).body(hospedeRepository.saveAndFlush(hospede));
    }

    public ResponseEntity<Hospede> findHospedeById(Integer id) {
        return hospedeRepository.findById(id)
                .map(hospede -> ResponseEntity.ok().body(hospede))
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    public ResponseEntity<Hospede> updateHospede(Integer id, HospedeRegistrationRequest request) {
        return hospedeRepository.findById(id)
                .map(hospede -> {
                    hospede.setNome(request.nome());
                    hospede.setSobrenome(request.sobrenome());
                    hospede.setDataNascimento(request.dataNascimento());
                    hospede.setNacionalidade(request.nacionalidade());
                    hospede.setTelefone(request.telefone());

                    Hospede updated = hospedeRepository.save(hospede);
                    return ResponseEntity.ok().body(updated);})
                .orElse(ResponseEntity.notFound()
                        .build());
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
