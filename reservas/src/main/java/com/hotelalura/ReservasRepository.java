package com.hotelalura;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservasRepository extends JpaRepository<Reservas, Integer> {
    Reservas findByReservasId(Integer reservasId);
}
