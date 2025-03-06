package com.ambercff.events_app.implementations.inscricao;

import com.ambercff.events_app.infra.exceptions.InscriptionNotFoundException;
import com.ambercff.events_app.infra.exceptions.UserNotFoundException;
import com.ambercff.events_app.models.Inscricao;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.repositories.InscricaoRepository;
import com.ambercff.events_app.repositories.UserRepository;
import com.ambercff.events_app.services.inscricao.InscricaoGetAllByUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoGetAllByUserImpl implements InscricaoGetAllByUserService {
    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Inscricao> getAllInscriptionsByUser(String email) {
        User user = (User) userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
        List<Inscricao> inscricoes = inscricaoRepository.findByParticipante(user);
        if(inscricoes.isEmpty()){
            throw new InscriptionNotFoundException("Nenhuma inscrição encontrada para esse usuário!");
        } else {
            return inscricoes;
        }
    }
}
