package com.kuldeep.zaika.security.jwt;

import com.kuldeep.zaika.security.UserAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class JwtAuthenticationFilter extends AuthenticationFilter {
    public JwtAuthenticationFilter(JwtTokenService jwtTokenService) {
        super(new JwtAuthenticationManager(jwtTokenService), new JwtAutenticationConvertor());
        this.setSuccessHandler(((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }));
    }

    static class JwtAutenticationConvertor implements AuthenticationConverter{

        public Authentication convert(HttpServletRequest request) {
            if(request.getHeader("Authorization")!=null){
                String token=request.getHeader("Authorization").replace("Bearer ","");
                return new UserAuthentication(token);
            }
            return null;
        }
    }
    static class JwtAuthenticationManager implements AuthenticationManager{
        private final JwtTokenService jwtTokenService;

        JwtAuthenticationManager(JwtTokenService jwtTokenService) {
            this.jwtTokenService = jwtTokenService;
        }

        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            if(authentication instanceof UserAuthentication){
                UserAuthentication userAuthentication=(UserAuthentication) authentication;
                String username=jwtTokenService.getUsernameFromToken(userAuthentication.getCredentials());
                if(username!=null){
                    userAuthentication.setUsername(username);
                    return userAuthentication;
                }
            }

            return null;
        }
    }




}