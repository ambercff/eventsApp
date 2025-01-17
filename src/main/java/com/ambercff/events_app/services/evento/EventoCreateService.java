package com.ambercff.events_app.services.evento;

import com.ambercff.events_app.dtos.evento.EventoCreateDTO;
import com.ambercff.events_app.models.Evento;

public interface EventoCreateService {
    Evento createEvento(EventoCreateDTO data);
}
