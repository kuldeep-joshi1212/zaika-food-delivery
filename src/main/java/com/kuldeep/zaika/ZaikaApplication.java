package com.kuldeep.zaika;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(ZaikaConfigProperties.class)
public class ZaikaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaikaApplication.class, args);
	}
	private final static String TOKEN_SERVICE_TYPE="JWT";
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}