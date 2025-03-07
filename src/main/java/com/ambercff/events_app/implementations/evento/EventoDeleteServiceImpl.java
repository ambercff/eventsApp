package com.ambercff.events_app.implementations.evento;

import com.ambercff.events_app.infra.exceptions.EventNotFoundException;
import com.ambercff.events_app.models.Evento;
import com.ambercff.events_app.repositories.EventoRepository;
import com.ambercff.events_app.services.evento.EventoDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventoDeleteServiceImpl implements EventoDeleteService {
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public String deleteEvent(Long idEvento) {
        Optional<Evento> eventoOptional = eventoRepository.findById(idEvento);
        if(eventoOptional.isPresent()){
            Evento evento = eventoOptional.get();
            eventoRepository.delete(evento);
            return "Evento deletado com sucesso!";
        } else {
            throw new EventNotFoundException("Evento não encontrado! Tente novamente.");
        }
    }
}
