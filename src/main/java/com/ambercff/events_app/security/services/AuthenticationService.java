package com.ambercff.events_app.security.services;

import com.ambercff.events_app.dtos.auth.AuthInputDTO;
import com.ambercff.events_app.dtos.auth.TokenDTO;
import com.ambercff.events_app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public TokenDTO loginAndCreateToken(AuthInputDTO data) {
        String token = tokenService.generateToken((User) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.senha())).getPrincipal());
        return new TokenDTO(token);
    }
}
