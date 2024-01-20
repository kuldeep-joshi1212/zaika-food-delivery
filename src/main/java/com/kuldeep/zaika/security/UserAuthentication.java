package com.kuldeep.zaika.security;

import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication implements Authentication {
    private final String token;
    @Setter
    private String username;

    public UserAuthentication(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //implement later
        return null;
    }

    @Override
    public String  getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        //implement later
        return null;
    }

    @Override
    public String getPrincipal() {
        return username;
    }

    @Override
    public boolean isAuthenticated() {
        return username!=null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if(!isAuthenticated){
            this.username = null;
        }
    }

    @Override
    public String getName() {
        return null;
    }
}