package com.odontoweb.arquitetura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.odontoweb.arquitetura.security.JWTAuthorizationUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfig {
	
	@Bean
	public JWTAuthorizationUtil authorizationUtils(){
		return new JWTAuthorizationUtil();
	}
	
	@Bean
	public PasswordEncoder encoder(){
		final Map<String, PasswordEncoder> encoders = new HashMap<>();
		final String idForEncode = "bcrypt";
		encoders.put(idForEncode, new BCryptPasswordEncoder());
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());

		final DelegatingPasswordEncoder rv = new DelegatingPasswordEncoder(idForEncode, encoders);

		return rv;
	}
	
}
