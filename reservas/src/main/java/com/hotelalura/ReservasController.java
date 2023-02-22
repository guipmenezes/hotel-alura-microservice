package com.hotelalura;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@AllArgsConstructor
@Slf4j
public class ReservasController {

    @Autowired
    private ReservasService reservasService;

    @GetMapping
    public List<Reservas> getReserva() {
        return reservasService.findReserva();
    }

    @GetMapping("/{id}")
    public Reservas findReservasById(@PathVariable("id") Integer reservasId) {
        return reservasService.findReservasById(reservasId);
    }

    @PostMapping
    public void registerReserva(@RequestBody ReservasRegistrationRequest reservasRegistrationRequest) {
        log.info("novo registro de reserva {}", reservasRegistrationRequest);
        reservasService.registerReserva(reservasRegistrationRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteReserva(@PathVariable("id") Integer reservaId) {
        log.info("Deletando a reserva...");
        reservasService.deleteReserva(reservaId);
    }
}
