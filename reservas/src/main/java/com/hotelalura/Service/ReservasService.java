package com.hotelalura.Service;

import com.hotelalura.Model.Reservas;
import com.hotelalura.Controller.ReservasRegistrationRequest;
import com.hotelalura.Model.ReservasRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReservasService {

    private final ReservasRepository reservasRepository;
    public List<Reservas> findReserva() {
        return reservasRepository.findAll();
    }

    public void registerReserva(ReservasRegistrationRequest request) {
        Reservas reserva = Reservas.builder()
                .roomType(request.roomType())
                .dataEntrada(request.dataEntrada())
                .dataSaida(request.dataSaida())
                .valor(request.valor())
                .formaPagamento(request.formaPagamento())
                .build();

        reservasRepository.saveAndFlush(reserva);
    }
    public Reservas findReservasById(Integer reservasId) {
        log.info("Encontrando uma nova reserva por meio do repositório");
        return reservasRepository.findByReservasId(reservasId);
    }

    public void deleteReserva(Integer reservaId) {
        reservasRepository.deleteById(reservaId);
    }
}
