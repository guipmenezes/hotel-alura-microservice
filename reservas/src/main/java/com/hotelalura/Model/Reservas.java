package com.hotelalura.Model;

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
    @GeneratedValue(strategy= GenerationType.UUID)
    private Integer reservasId;
    private String roomType;
    private Date dataEntrada;
    private Date dataSaida;
    private Double valor;
    private String formaPagamento;
}
