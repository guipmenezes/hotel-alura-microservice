package com.hotelalura;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservas {
    @Id
    @SequenceGenerator(name="reserva_id_sequence", sequenceName="reserva_id_sequence", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="reserva_id_sequence")
    private Integer reservasId;
    private Date dataEntrada;
    private Date dataSaida;
    private Integer valor;
    private String formaPagamento;
}
