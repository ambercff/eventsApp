package com.ambercff.events_app.security.services;

import com.ambercff.events_app.infra.exceptions.UserDeactivatedException;
import com.ambercff.events_app.infra.exceptions.UserNotFoundException;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = (User) userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
        if(!user.getAtivo()){
            throw new UserDeactivatedException("Usuário desativado! Por favor, ative-o novamente para fazer o login");
        } else {
            return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
        }
    }
}
