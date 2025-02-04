package com.ambercff.events_app.controllers;

import com.ambercff.events_app.dtos.evento.EventoCreateDTO;
import com.ambercff.events_app.dtos.evento.EventoDTO;
import com.ambercff.events_app.models.Evento;
import com.ambercff.events_app.services.evento.EventoCreateService;
import com.ambercff.events_app.services.evento.EventoGetAllService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class EventoController {
    @Autowired
    EventoCreateService eventoCreateService;

    @Autowired
    EventoGetAllService eventoGetAllService;

    // POST
    @MutationMapping
    public EventoDTO createEvent(@Argument @Valid EventoCreateDTO data){
        final var event = eventoCreateService.createEvento(data);

        return EventoDTO.builder()
                .idEvento(event.getIdEvento())
                .titulo(event.getTitulo())
                .descricao(event.getDescricao())
                .data(event.getData())
                .local(event.getLocal())
                .capacidadeMaxima(event.getCapacidadeMaxima())
                .organizadores(event.getOrganizadores())
                .build();
    }

    // GET
    @QueryMapping
    public List<EventoDTO> getAllEvents(){
        final List<Evento> eventos = eventoGetAllService.getAllEvents();

        return eventos.stream().map(evento -> EventoDTO.builder()
                .idEvento(evento.getIdEvento())
                .titulo(evento.getTitulo())
                .descricao(evento.getDescricao())
                .data(evento.getData())
                .local(evento.getLocal())
                .capacidadeMaxima(evento.getCapacidadeMaxima())
                .organizadores(evento.getOrganizadores()).build()
        ).toList();
    }
}
