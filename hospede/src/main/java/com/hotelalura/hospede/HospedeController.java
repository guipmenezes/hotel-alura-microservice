package com.hotelalura.hospede;

import com.hotelalura.hospede.VO.ResponseTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping
    public void registerHospede(@RequestBody HospedeRegistrationRequest hospedeRegistrationRequest) {
        log.info("novo registro de hospede {}", hospedeRegistrationRequest);
        hospedeService.registerHospede(hospedeRegistrationRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteHospede(@PathVariable("id") Integer id) {
        log.info("deletando o hospede de id {}", id);
        hospedeService.deleteHospede(id);
    }

    @GetMapping("/reserva/{id}")
    public ResponseTemplate getHospedeWithReserva(@PathVariable("id") Integer hospedeId) {
        log.info("Trazendo o hospede com a reserva");
        return hospedeService.getHospedeWithReserva(hospedeId);
    }
}
