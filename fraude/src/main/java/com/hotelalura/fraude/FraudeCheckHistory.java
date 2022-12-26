package com.hotelalura.fraude;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FraudeCheckHistory {
    @Id
    @SequenceGenerator(
            name = "fraude_id_sequence",
            sequenceName = "fraude_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraude_id_sequence"
    )
    private Integer id;
    private Integer hospedeId;
    private Boolean isFraude;
    private LocalDateTime createdAt;
}
