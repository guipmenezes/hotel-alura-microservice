package com.hotelalura;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackMethodController {

    @RequestMapping("/hospedeServiceFallBack")
    public String hospedeServiceFallBackMethod() {
        return "O sistema de hospede está demorando mais do que o esperado." + "Por favor tente novamente";
    }

    @RequestMapping("reservaServiceFallBack")
    public String reservasServiceFallBackMethod() {
        return "O sistema de reserva está demorando mais do que o esperado." + "Por favor tente novamente";
    }

    @RequestMapping("/fraudeServiceFallBack")
    public String fraudeServiceFallBackMethod() {
        return "O sistema de checkagem de fraude está demorando mais do que o esperado." + "Por favor tente novamente";
    }
}
