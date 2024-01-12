package com.kuldeep.zaika.security;

public interface LoginTokenService {
    public String createToken(String username);
    public String getUsernameFromToken(String Token);
}