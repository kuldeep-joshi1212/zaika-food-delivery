package com.kuldeep.zaika;

import com.kuldeep.zaika.security.LoginTokenService;
import com.kuldeep.zaika.security.LoginTokenServiceImplementation;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ZaikaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaikaApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public LoginTokenService tokenService(){
		return new LoginTokenServiceImplementation();
	}
}