package com.hotelalura.fraude;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudeCheckService {
    private final FraudeCheckHistoryRepository fraudeCheckHistoryRepository;

    public boolean isFradulentHospede(Integer hospedeId) {
        fraudeCheckHistoryRepository.save(
                FraudeCheckHistory.builder()
                        .hospedeId(hospedeId)
                        .isFraude(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
