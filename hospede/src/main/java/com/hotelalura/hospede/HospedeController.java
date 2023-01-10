package com.hotelalura.hospede;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hospedes")
@AllArgsConstructor
public class HospedeController {

    @Autowired
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
