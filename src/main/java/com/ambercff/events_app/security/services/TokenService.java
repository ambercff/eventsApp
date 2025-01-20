package com.ambercff.events_app.security.services;

import com.ambercff.events_app.infra.exceptions.InvalidTokenException;
import com.ambercff.events_app.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) throws JWTCreationException {
        try {
            return JWT
                    .create()
                    .withIssuer("events_app")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getIdUser())
                    .withExpiresAt(Expirate())
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token!", e);
        }
    }

    public String getSubject(String token) throws JWTCreationException, JWTDecodeException, InvalidTokenException {
        try {
            return JWT
                    .require(Algorithm.HMAC256(secret))
                    .withIssuer("events_app")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTDecodeException e){
            throw new InvalidTokenException("Token inválido!");
        } catch (TokenExpiredException e) {
            throw new TokenExpiredException("Token expirado!", e.getExpiredOn());
        }
    }

    private Instant Expirate(){
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
