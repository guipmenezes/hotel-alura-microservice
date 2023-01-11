package com.hotelalura.hospede.VO;

import com.hotelalura.hospede.Hospede;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {

    private Hospede hospede;
    private Reservas reservas;
}
