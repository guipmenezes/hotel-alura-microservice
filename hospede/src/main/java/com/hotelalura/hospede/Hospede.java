package com.hotelalura.hospede;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hospede {

    @Id
    @SequenceGenerator(name = "hospede_id_sequence", sequenceName = "hospede_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospede_id_sequence")
    private Integer hospedeId;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String nacionalidade;
    private String telefone;
}
