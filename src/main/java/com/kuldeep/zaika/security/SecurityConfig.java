package com.kuldeep.zaika.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeHttpRequests()//csfr is enabled by default so no one can directly make a post request, we first make a get request and generate a token to make a post request
                .requestMatchers(HttpMethod.POST ,"/user/signup","/user/login").permitAll()
                .requestMatchers(HttpMethod.GET ,"/ping").permitAll()
                .anyRequest().authenticated();
        return http.build();
    }
}