package com.hotelalura.hospede.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HospedeRepository extends JpaRepository<Hospede, Integer> {
    Hospede findByHospedeId(Integer hospedeId);
}
