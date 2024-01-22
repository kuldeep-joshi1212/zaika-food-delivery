package com.kuldeep.zaika.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kuldeep.zaika.ZaikaConfigProperties;
import com.kuldeep.zaika.exceptions.TokenException;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

@Service
public class JwtTokenServiceImplementation implements JwtTokenService {
    private final ZaikaConfigProperties zaikaConfigProperties;
    private final Algorithm algorithm;
    public JwtTokenServiceImplementation(ZaikaConfigProperties zaikaConfigProperties) {
        this.zaikaConfigProperties = zaikaConfigProperties;
        this.algorithm = Algorithm.HMAC256(zaikaConfigProperties.key());
    }

    public String createToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer(zaikaConfigProperties.issuer())
                .withIssuedAt(new java.util.Date())
                .withExpiresAt(new java.util.Date(System.currentTimeMillis()+zaikaConfigProperties.expiry()))
                .sign(algorithm);

    }

    public String getUsernameFromToken(String token) throws TokenException {
        JWTVerifier verifier=JWT.require(algorithm)
                .withIssuer(zaikaConfigProperties.issuer())
                .build();
        DecodedJWT decodedJWT=verifier.verify(token);
        return  decodedJWT.getSubject();
    }
    public Boolean validateToken(String token,String username)throws TokenException {
        JWTVerifier verifier=JWT.require(algorithm)
                .withIssuer(zaikaConfigProperties.issuer())
                .build();
        DecodedJWT decodedJWT=verifier.verify(token);
        Date expiry=decodedJWT.getExpiresAt();
        if(expiry.before(new Date())){
            throw new TokenException("Token Expired");
        }
        if(!getUsernameFromToken(token).equals(username)){
            return false;
        }
        return true;
    }

    public String convertTokenToString(String token) {
        return token.replace("Bearer ","");
    }

}