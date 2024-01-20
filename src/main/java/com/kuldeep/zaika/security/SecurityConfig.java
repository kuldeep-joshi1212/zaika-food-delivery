package com.kuldeep.zaika.security;

import com.kuldeep.zaika.security.jwt.JwtAuthenticationFilter;
import com.kuldeep.zaika.security.jwt.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenService jwtTokenService;
    @Autowired
    public SecurityConfig(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer ::disable)
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenService),AnonymousAuthenticationFilter.class)
                .authorizeHttpRequests((requests)-> requests
                        .requestMatchers(HttpMethod.POST ,"/user/signup","/user/login","/restaurant/getAll").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}