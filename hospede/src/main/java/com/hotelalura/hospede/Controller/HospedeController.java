package com.hotelalura.hospede.Controller;

import com.hotelalura.hospede.Model.Hospede;
import com.hotelalura.hospede.Service.HospedeService;
import com.hotelalura.hospede.VO.ResponseTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hospedes")
@AllArgsConstructor
public class HospedeController {

    private final HospedeService hospedeService;

    @GetMapping
    public List<Hospede> getHospede() { return hospedeService.findHospede(); }

    @GetMapping("/{id}")
    public ResponseEntity<Hospede> getHospedeById(@PathVariable Integer id) {
        log.info("trazendo registro de hospede por id");
        return hospedeService.findHospedeById(id);
    }

    @PostMapping
    public ResponseEntity<Hospede> registerHospede(@RequestBody HospedeRegistrationRequest request) {
        log.info("novo registro de hospede {}", request);
        return hospedeService.registerHospede(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospede(@PathVariable("id") Integer id) {
        log.info("deletando o hospede de id {}", id);
        return hospedeService.deleteHospede(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospede> updateHospede(@PathVariable("id") Integer id,
                                                 HospedeRegistrationRequest request) {
        return hospedeService.updateHospede(id, request);
    }

    @GetMapping("/reserva/{id}")
    public ResponseTemplate getHospedeWithReserva(@PathVariable("id") Integer hospedeId) {
        log.info("Trazendo o hospede com a reserva");
        return hospedeService.getHospedeWithReserva(hospedeId);
    }
}
