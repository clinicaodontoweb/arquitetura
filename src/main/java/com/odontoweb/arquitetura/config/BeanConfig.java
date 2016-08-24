package com.odontoweb.arquitetura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.odontoweb.arquitetura.security.JWTAuthorizationUtil;

@Configuration
public class BeanConfig {
	
	@Bean
	public JWTAuthorizationUtil authorizationUtils(){
		return new JWTAuthorizationUtil();
	}
	
	@Bean
	public Md5PasswordEncoder encoder(){
		return new Md5PasswordEncoder();
	}
	
}
