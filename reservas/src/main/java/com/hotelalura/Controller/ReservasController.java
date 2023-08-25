package com.hotelalura.Controller;

import Constants.RabbitMQConstants;
import DTO.ReservasRegistrationRequest;
import com.hotelalura.Model.Reservas;
import com.hotelalura.RabbitMQ.Service.RabbitMQService;
import com.hotelalura.Service.ReservasService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@AllArgsConstructor
@Slf4j
public class ReservasController {

    @Autowired
    private ReservasService reservasService;
    private RabbitMQService rabbitMQService;

    @GetMapping
    public List<Reservas> getReserva() {
        return reservasService.findReserva();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservas> findReservasById(@PathVariable("id") Integer reservasId) {
        return reservasService.findReservasById(reservasId);
    }

    @PostMapping
    public ResponseEntity<Reservas> registerReserva(@RequestBody ReservasRegistrationRequest request) {
        log.info("novo registro de reserva {}", request);
        this.rabbitMQService.sendMessage(RabbitMQConstants.RESERVATION_QUEUE, request);
        return reservasService.registerReserva(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateReserva(@PathVariable("id") Integer reservaId,
                                        @RequestBody ReservasRegistrationRequest request) {
        log.info("atualizando a reserva {}", request);
        return reservasService.updateReserva(reservaId, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable("id") Integer reservaId) {
        log.info("Deletando a reserva...");
        return reservasService.deleteReserva(reservaId);
    }
}
