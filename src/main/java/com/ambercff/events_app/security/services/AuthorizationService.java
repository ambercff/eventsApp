package com.ambercff.events_app.security.services;

import com.ambercff.events_app.dtos.user.UserCreateDTO;
import com.ambercff.events_app.infra.exceptions.UserAlreadyExistsException;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User register(UserCreateDTO data){
        userRepository.findByEmail(data.email())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("Usuário já existente!");
                });
        User user = User.builder()
                .nome(data.nome())
                .senha(passwordEncoder.encode(data.senha()))
                .email(data.email())
                .userRole(data.userRole())
                .ativo(true)
                .build();

        userRepository.save(user);
        return user;
    }
}
