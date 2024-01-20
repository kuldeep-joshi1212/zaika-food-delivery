package com.kuldeep.zaika.security.jwt;

public interface JwtTokenService {
    public String createToken(String username);
    public String getUsernameFromToken(String Token);
    public Boolean validateToken(String token,String username);
    public String convertTokenToString(String token);
}