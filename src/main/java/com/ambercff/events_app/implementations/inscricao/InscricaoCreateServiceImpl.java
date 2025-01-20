package com.ambercff.events_app.implementations.inscricao;

import com.ambercff.events_app.dtos.inscricao.InscricaoCreateDTO;
import com.ambercff.events_app.infra.exceptions.EventNotFoundException;
import com.ambercff.events_app.infra.exceptions.UserNotFoundException;
import com.ambercff.events_app.models.Inscricao;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.models.enums.StatusInscricao;
import com.ambercff.events_app.repositories.EventoRepository;
import com.ambercff.events_app.repositories.InscricaoRepository;
import com.ambercff.events_app.repositories.UserRepository;
import com.ambercff.events_app.services.inscricao.InscricaoCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscricaoCreateServiceImpl implements InscricaoCreateService {
    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public Inscricao createInscription(InscricaoCreateDTO data) {
        return inscricaoRepository.save(
                Inscricao.builder()
                        .participante((User) userRepository.findByEmail(data.email()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!")))
                        .evento(eventoRepository.findById(data.idEvento()).orElseThrow(() -> new EventNotFoundException("Evento não encontrado!")))
                        .statusInscricao(StatusInscricao.CONFIRMADA).build()

        );
    }
}
