package com.hotelalura.fraude;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {
    private final FraudeCheckService fraudeCheckService;

    @GetMapping(path = "{hospedeId}")
    public FraudCheckResponse isFraudster(@PathVariable("hospedeId") Integer hospedeId) {

        Boolean isFraudulentHospede = fraudeCheckService.isFradulentHospede(hospedeId);
        return new FraudCheckResponse(isFraudulentHospede);
    }
}
