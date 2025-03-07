package com.ambercff.events_app.implementations.inscricao;

import com.ambercff.events_app.infra.exceptions.InscriptionNotFoundException;
import com.ambercff.events_app.models.Inscricao;
import com.ambercff.events_app.models.enums.StatusInscricao;
import com.ambercff.events_app.repositories.InscricaoRepository;
import com.ambercff.events_app.services.inscricao.InscricaoCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InscricaoCancelServiceImpl implements InscricaoCancelService {
    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Override
    public String cancelInscription(Long idInscricao) {
        Optional<Inscricao> inscricaoOptional = inscricaoRepository.findById(idInscricao);
        if(inscricaoOptional.isPresent()){
            Inscricao inscricao = inscricaoOptional.get();
            if(inscricao.getStatusInscricao() == StatusInscricao.CANCELADA){
                return "A inscrição já está cancelada!";
            } else {
                inscricao.setStatusInscricao(StatusInscricao.CANCELADA);
                inscricaoRepository.save(inscricao);
                return "Inscrição cancelada com sucesso!";
            }
        } else {
            throw new InscriptionNotFoundException("Inscrição não encontrada!");
        }
    }
}
