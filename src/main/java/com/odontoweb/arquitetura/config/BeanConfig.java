package com.odontoweb.arquitetura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.odontoweb.arquitetura.security.JWTAuthorizationUtil;

@Configuration
public class BeanConfig {
	
	@Bean
	public JWTAuthorizationUtil authorizationUtils(){
		return new JWTAuthorizationUtil();
	}
	
}
