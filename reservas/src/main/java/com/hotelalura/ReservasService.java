package com.hotelalura;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservasService {

    private final ReservasRepository reservasRepository;
    public List<Reservas> getReserva() {
        return reservasRepository.findAll();
    }

    public void registerReserva(ReservasRegistrationRequest request) {
        Reservas reserva = Reservas.builder()
                .dataEntrada(request.dataEntrada())
                .dataSaida(request.dataSaida())
                .valor(request.valor())
                .formaPagamento(request.formaPagamento())
                .build();

        reservasRepository.saveAndFlush(reserva);
    }
}
