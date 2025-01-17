package com.ambercff.events_app.implementations.evento;

import com.ambercff.events_app.models.Evento;
import com.ambercff.events_app.repositories.EventoRepository;
import com.ambercff.events_app.services.evento.EventoGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoGetAllServiceImpl implements EventoGetAllService {
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public List<Evento> getAllEvents() {
        return eventoRepository.findAll();
    }
}
