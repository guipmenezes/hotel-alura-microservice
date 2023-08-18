package com.hotelalura.Model;

import com.hotelalura.Model.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservasRepository extends JpaRepository<Reservas, Integer> {
    Reservas findByReservasId(Integer reservasId);
}
