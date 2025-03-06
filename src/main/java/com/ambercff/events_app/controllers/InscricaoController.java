package com.ambercff.events_app.controllers;

import com.ambercff.events_app.dtos.inscricao.InscricaoCreateDTO;
import com.ambercff.events_app.dtos.inscricao.InscricaoDTO;
import com.ambercff.events_app.models.Inscricao;
import com.ambercff.events_app.services.inscricao.InscricaoCreateService;
import com.ambercff.events_app.services.inscricao.InscricaoGetAllByUserService;
import com.ambercff.events_app.services.inscricao.InscricaoGetAllService;
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
public class InscricaoController {
    @Autowired
    InscricaoCreateService inscricaoCreateService;

    @Autowired
    InscricaoGetAllService inscricaoGetAllService;

    @Autowired
    InscricaoGetAllByUserService inscricaoGetAllByUserService;

    @MutationMapping
    public InscricaoDTO createInscription(@Argument @Valid InscricaoCreateDTO data){
        final var inscription = inscricaoCreateService.createInscription(data);

        return InscricaoDTO.builder()
                .idInscricao(inscription.getIdInscricao())
                .participante(inscription.getParticipante())
                .evento(inscription.getEvento())
                .statusInscricao(inscription.getStatusInscricao())
                .build();
    }

    @QueryMapping
    public List<InscricaoDTO> getAllInscriptions(){
        final List<Inscricao> inscriptions = inscricaoGetAllService.getAllInscriptions();

        return inscriptions.stream().map(inscription -> InscricaoDTO.builder()
                .idInscricao(inscription.getIdInscricao())
                .participante(inscription.getParticipante())
                .evento(inscription.getEvento())
                .statusInscricao(inscription.getStatusInscricao())
                .build()
        ).toList();
    }

    @MutationMapping
    public List<InscricaoDTO> getAllInscriptionsByUser(@Argument String email){
        final List<Inscricao> inscriptions = inscricaoGetAllByUserService.getAllInscriptionsByUser(email);

        return inscriptions.stream().map(inscricao -> InscricaoDTO.builder()
                .idInscricao(inscricao.getIdInscricao())
                .participante(inscricao.getParticipante())
                .evento(inscricao.getEvento())
                .statusInscricao(inscricao.getStatusInscricao())
                .build()
        ).toList();
    }
}
