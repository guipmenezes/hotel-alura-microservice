package com.hotelalura.hospede.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservas {
    private Integer reservasId;
    private Date dataEntrada;
    private Date dataSaida;
    private Integer valor;
    private String formaPagamento;
}
