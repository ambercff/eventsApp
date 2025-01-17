package com.ambercff.events_app.implementations.user;

import com.ambercff.events_app.dtos.user.UserCreateDTO;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.repositories.UserRepository;
import com.ambercff.events_app.services.user.UserCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateServiceImpl implements UserCreateService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserCreateDTO data) {
        return userRepository.save(
                User.builder()
                        .nome(data.nome())
                        .email(data.email())
                        .senha(data.senha())
                        .userRole(data.userRole())
                        .ativo(true)
                        .build()
        );
    }
}
