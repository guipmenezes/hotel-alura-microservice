package com.hotelalura.fraude;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudeCheckHistoryRepository extends JpaRepository<FraudeCheckHistory, Integer> {
}
