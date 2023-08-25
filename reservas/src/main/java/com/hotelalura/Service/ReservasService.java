package com.hotelalura.Service;

import DTO.ReservasRegistrationRequest;
import com.hotelalura.Model.Reservas;
import com.hotelalura.Model.ReservasRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Reservas> registerReserva(ReservasRegistrationRequest request) {
        Reservas reserva = Reservas.builder()
                .roomType(request.roomType())
                .dataEntrada(request.dataEntrada())
                .dataSaida(request.dataSaida())
                .valor(request.valor())
                .formaPagamento(request.formaPagamento())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(reservasRepository.saveAndFlush(reserva));
    }
    public ResponseEntity<Reservas> findReservasById(Integer reservasId) {
        log.info("Encontrando uma nova reserva por meio do repositório");
        return reservasRepository.findById(reservasId)
                .map(reserva -> ResponseEntity.ok().body(reserva))
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    public ResponseEntity<Reservas> updateReserva(Integer reservaId, ReservasRegistrationRequest request) {
        return reservasRepository.findById(reservaId)
                .map(reserva -> {
                    reserva.setRoomType(request.roomType());
                    reserva.setDataEntrada(request.dataEntrada());
                    reserva.setDataSaida(request.dataSaida());
                    reserva.setValor(request.valor());
                    reserva.setFormaPagamento(request.formaPagamento());

                    Reservas updated = reservasRepository.save(reserva);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    public ResponseEntity<Void> deleteReserva(Integer reservaId) {
        return reservasRepository.findById(reservaId)
                .map(reserva -> {
                    reservasRepository.deleteById(reservaId);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound()
                        .build());
    }
}
