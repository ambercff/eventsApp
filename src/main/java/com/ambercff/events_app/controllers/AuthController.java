package com.ambercff.events_app.controllers;

import com.ambercff.events_app.dtos.auth.AuthInputDTO;
import com.ambercff.events_app.dtos.auth.TokenDTO;
import com.ambercff.events_app.dtos.user.UserCreateDTO;
import com.ambercff.events_app.dtos.user.UserDTO;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.security.services.AuthenticationService;
import com.ambercff.events_app.security.services.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthorizationService authorizationService;

    @MutationMapping
    public TokenDTO login(@Argument @Valid AuthInputDTO data){
        return authenticationService.loginAndCreateToken(data);
    }

    @MutationMapping
    public UserDTO register(@Argument @Valid UserCreateDTO data){
        User user = authorizationService.register(data);
        return UserDTO.builder()
                .idUser(user.getIdUser())
                .nome(user.getNome())
                .email(user.getEmail())
                .senha(user.getSenha())
                .userRole(user.getUserRole())
                .ativo(user.getAtivo())
                .eventos(user.getEventos()).build();
    }
}
