package com.hotelalura.hospede;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/hospedes")
@AllArgsConstructor
public class HospedeController {
    private final HospedeService hospedeService;

    @PostMapping
    public void registerHospede(@RequestBody HospedeRegistrationRequest hospedeRegistrationRequest) {
        log.info("novo registro de hospede {}", hospedeRegistrationRequest);
        hospedeService.registerHospede(hospedeRegistrationRequest);
    }
}
