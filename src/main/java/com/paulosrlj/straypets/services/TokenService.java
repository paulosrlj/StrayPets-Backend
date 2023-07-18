package com.paulosrlj.straypets.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.paulosrlj.straypets.domain.entities.User;
import com.paulosrlj.straypets.exception.JwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("StrayPetsApi")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new JwtException("Ocorreu um erro ao gerar o token", ex);
        }
    }

    public String validateToken(String token){
        try {
             Algorithm algorithm = Algorithm.HMAC256(secret);
             return JWT.require(algorithm)
                     .withIssuer("StrayPetsApi")
                     .build()
                     .verify(token)
                     .getSubject();
        } catch (JWTVerificationException ex) {
            throw new JwtException("Ocorreu um erro ao validar o token", ex);
        }
    }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
