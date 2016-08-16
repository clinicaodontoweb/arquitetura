package com.odontoweb.arquitetura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.odontoweb.arquitetura.authorization.JWTAuthorizationUtil;
import com.odontoweb.arquitetura.properties.Properties;

@Configuration
public class BeanConfig {
	
	@Bean
	public JWTAuthorizationUtil authorizationUtils(Properties properties){
		return new JWTAuthorizationUtil(properties.getKey());
	}
	
	@Bean
	public Properties properties(){
		return new Properties();
	}
	
}
