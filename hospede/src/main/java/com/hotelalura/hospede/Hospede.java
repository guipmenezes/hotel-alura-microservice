package com.hotelalura.hospede;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HOSPEDE")
public class Hospede {
    @Id
    @SequenceGenerator(
            name = "hospede_id_sequence",
            sequenceName = "hospede_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hospede_id_sequence"
    )
    private Integer id;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String nacionalidade;
    private String telefone;
}
