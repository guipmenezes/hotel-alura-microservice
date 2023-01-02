package com.hotelalura.hospede;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
}
