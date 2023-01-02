package com.hotelalura.fraude;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fraude-check")
@AllArgsConstructor
@Slf4j
public class FraudeController {
    private final FraudeCheckService fraudeCheckService;

    @GetMapping(path = "{hospedeId}")
    public FraudeCheckResponse isFraudster(@PathVariable("hospedeId") Integer hospedeId) {

        Boolean isFraudulentHospede = fraudeCheckService.isFradulentHospede(hospedeId);
        log.info("Verificação de fraude requisitada para o hospede {}", hospedeId);
        return new FraudeCheckResponse(isFraudulentHospede);
    }

    @DeleteMapping("/{id}")
    public void deleteFraude(@PathVariable("id") Integer id) {
        fraudeCheckService.deleteFraude(id);
    }
}
