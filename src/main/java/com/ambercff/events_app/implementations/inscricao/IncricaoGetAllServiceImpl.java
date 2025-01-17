package com.ambercff.events_app.implementations.inscricao;

import com.ambercff.events_app.models.Inscricao;
import com.ambercff.events_app.repositories.InscricaoRepository;
import com.ambercff.events_app.services.inscricao.InscricaoGetAllService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IncricaoGetAllServiceImpl implements InscricaoGetAllService {
    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Override
    public List<Inscricao> getAllInscriptions() {
        return inscricaoRepository.findAll();
    }
}
