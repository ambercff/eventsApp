package com.ambercff.events_app.controllers;

import com.ambercff.events_app.dtos.user.UserCreateDTO;
import com.ambercff.events_app.dtos.user.UserDTO;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.services.user.UserCreateService;
import com.ambercff.events_app.services.user.UserDeactivateService;
import com.ambercff.events_app.services.user.UserGetAllService;
import com.ambercff.events_app.services.user.UserReactivateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
    UserGetAllService userGetAllService;

    @Autowired
    UserDeactivateService userDeactivateService;

    @Autowired
    UserReactivateService userReactivateService;

    @QueryMapping
    public List<UserDTO> getAllUsers(){
        final List<User> users = userGetAllService.getAllUsers();

        return users.stream().map(user -> UserDTO.builder()
                .idUser(user.getIdUser())
                .nome(user.getNome())
                .email(user.getEmail())
                .senha(user.getSenha())
                .userRole(user.getUserRole())
                .ativo(user.getAtivo())
                .eventos(user.getEventos()).build()
        ).toList();
    }

    @MutationMapping
    public String deactivateUser(@Argument String email){
        return userDeactivateService.deactivateUser(email);
    }

    @MutationMapping
    public String reactivateUser(@Argument String email){
        return userReactivateService.reactivateUser(email);
    }
}
