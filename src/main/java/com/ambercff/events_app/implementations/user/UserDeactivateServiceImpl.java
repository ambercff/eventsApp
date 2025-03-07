package com.ambercff.events_app.implementations.user;

import com.ambercff.events_app.infra.exceptions.UserNotFoundException;
import com.ambercff.events_app.models.Inscricao;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.models.enums.StatusInscricao;
import com.ambercff.events_app.repositories.InscricaoRepository;
import com.ambercff.events_app.repositories.UserRepository;
import com.ambercff.events_app.services.user.UserDeactivateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDeactivateServiceImpl implements UserDeactivateService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Override
    public String deactivateUser(String email) {
        User user = (User) userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
        if(user.getAtivo() == false){
            return "Usuário já desativado!";
        } else {
            user.setAtivo(false);
            List<Inscricao> inscricoes = inscricaoRepository.findByParticipante(user);
            if(!inscricoes.isEmpty()){
                inscricoes.forEach(inscricao -> inscricao.setStatusInscricao(StatusInscricao.CANCELADA));
                inscricaoRepository.saveAll(inscricoes);
            }
            userRepository.save(user);
            return "Usuário desativado com sucesso!";
        }
    }
}
