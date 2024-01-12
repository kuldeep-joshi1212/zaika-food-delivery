package com.kuldeep.zaika.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kuldeep.zaika.exceptions.TokenException;
import lombok.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

@Service
public class LoginTokenServiceImplementation implements LoginTokenService {
    private final Algorithm algorithm;
    private final String secret_key="you need to change this so it pick value from environment variable";
    private final long tokenExpiry=24*60*60*100;
    private final String issuer="kuldeep";

    public LoginTokenServiceImplementation() {
        this.algorithm = Algorithm.HMAC256(secret_key);
    }

    public String createToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new java.util.Date())
                .withExpiresAt(new java.util.Date(System.currentTimeMillis()+tokenExpiry))
                .sign(algorithm);

    }

    public String getUsernameFromToken(String token) throws TokenException {
        JWTVerifier verifier=JWT.require(algorithm)
                .withIssuer(issuer)
                .build();

        DecodedJWT decodedJWT=verifier.verify(token);
        return  decodedJWT.getSubject();
    }
}