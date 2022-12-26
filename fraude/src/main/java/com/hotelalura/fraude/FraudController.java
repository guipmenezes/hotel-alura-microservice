package com.hotelalura.fraude;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {
    private final FraudeCheckService fraudeCheckService;

    @GetMapping(path = "{hospedeId}")
    public FraudCheckResponse isFraudster(@PathVariable("hospedeId") Integer hospedeId) {

        Boolean isFraudulentHospede = fraudeCheckService.isFradulentHospede(hospedeId);
        log.info("Verificação de fraude requisitada para o hospede {}", hospedeId);
        return new FraudCheckResponse(isFraudulentHospede);
    }
}
