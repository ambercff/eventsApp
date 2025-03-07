package com.ambercff.events_app.implementations.user;

import com.ambercff.events_app.infra.exceptions.UserNotFoundException;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.repositories.UserRepository;
import com.ambercff.events_app.services.user.UserReactivateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReactivateServiceImpl implements UserReactivateService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String reactivateUser(String email) {
        User user = (User) userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
        if(user.getAtivo()){
            return "Usuário já ativo!";
        } else {
            user.setAtivo(true);
            return "Usuário reativado com sucesso!";
        }
    }
}

