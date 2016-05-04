package com.odontoweb.arquitetura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.odontoweb.arquitetura.properties.Properties;
import com.odontoweb.arquitetura.utils.AuthorizationUtils;

@Configuration
public class BeanConfig {

	@Bean
	public AuthorizationUtils authorizationUtils(Properties properties){
		return new AuthorizationUtils(properties.getKey());
	}
	
	@Bean
	public Properties properties(){
		return new Properties();
	}
}
