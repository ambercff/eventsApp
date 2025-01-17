package com.ambercff.events_app.repositories;

import com.ambercff.events_app.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
