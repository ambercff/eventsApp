package com.ambercff.events_app.repositories;

import com.ambercff.events_app.models.Evento;
import com.ambercff.events_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    Evento findByTitulo(String titulo);

    List<Evento> findAllByOrganizadores(User user);
}
