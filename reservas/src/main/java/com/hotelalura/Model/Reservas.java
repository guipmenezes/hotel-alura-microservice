package com.hotelalura.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservas {
    @Id
    @SequenceGenerator(name = "reserva_id_sequence", sequenceName = "reserva_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_id_sequence")
    private Integer reservasId;

    @Column(name = "roomtype", length = 50, nullable = false)
    @Pattern(regexp = "Quarto simples|Suíte|Suíte presidencial")
    private String roomType;

    @Column(name = "checkin", nullable = false)
    private Date dataEntrada;

    @Column(name = "checkout", nullable = false)
    private Date dataSaida;

    @Column(name = "value", nullable = false)
    @Min(50)
    @Max(10000)
    private Double valor;

    @Column(name = "paymentform", nullable = false)
    @Pattern(regexp = "Boleto|Cartão de crédito|Cartão de débito|Pix")
    private String formaPagamento;
}
