package edu.xanderson.PatoCritico.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;

@Service
public class TokenService {
    @Value("${token.secret.key}")
    private String secretKey;
    
    @Value("${token.tempo.duracao.horas}")
    private int tempo_duaracao;

    public String gerarToken(UsuarioEntity usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                            .withIssuer("auth-api")
                            .withSubject(usuario.getEmail())
                            .withExpiresAt(gerarDataExpiracao())
                            .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro na geração do token \n" + e);
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                        .withIssuer("auth-api")
                        .build()
                        .verify(token)
                        .getSubject();
        } catch (JWTVerificationException e) {
            System.err.println(e);
            return "";
        }
    }

    private Instant gerarDataExpiracao(){
        return LocalDateTime.now().plusHours(tempo_duaracao).toInstant(ZoneOffset.of("-03:00"));
    }
}
