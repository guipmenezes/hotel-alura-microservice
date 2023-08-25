package DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

public class ReservasRegistrationRequest implements Serializable {

        public String roomType;
        @DateTimeFormat(pattern="yyyy-MM-dd")
        public Date dataEntrada;
        @DateTimeFormat(pattern="yyyy-MM-dd")
        public Date dataSaida;
        public Double valor;
        public String formaPagamento;
}
