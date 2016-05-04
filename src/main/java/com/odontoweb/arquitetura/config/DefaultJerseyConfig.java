package com.odontoweb.arquitetura.config;

import org.glassfish.jersey.server.ResourceConfig;

import com.odontoweb.arquitetura.exceptionMappers.AuthHeaderNotFoundExceptionMapper;
import com.odontoweb.arquitetura.filter.AuthorizationFilter;

public class DefaultJerseyConfig extends ResourceConfig{
	
	public DefaultJerseyConfig() {}
	
	public void enableAuthorizationFilter(){
		register(AuthorizationFilter.class);
		register(AuthHeaderNotFoundExceptionMapper.class);
	}
}
