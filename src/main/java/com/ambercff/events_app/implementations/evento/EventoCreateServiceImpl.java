package com.ambercff.events_app.implementations.evento;

import com.ambercff.events_app.dtos.evento.EventoCreateDTO;
import com.ambercff.events_app.models.Evento;
import com.ambercff.events_app.repositories.EventoRepository;
import com.ambercff.events_app.services.evento.EventoCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoCreateServiceImpl implements EventoCreateService {
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public Evento createEvento(EventoCreateDTO data) {
        return eventoRepository.save(
                Evento.builder()
                        .titulo(data.titulo())
                        .descricao(data.descricao())
                        .data(data.data())
                        .local(data.local())
                        .capacidadeMaxima(data.capacidadeMaxima()).build()
        );
    }
}
