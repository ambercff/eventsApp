package com.ambercff.events_app.repositories;

import com.ambercff.events_app.models.Inscricao;
import com.ambercff.events_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    List<Inscricao> findByParticipante(User user);
}
