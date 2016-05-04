package com.odontoweb.arquitetura.model;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class CustomSecurityContext implements SecurityContext{

	private CustomPrincipal customPrincipal;
	
	public CustomSecurityContext(CustomPrincipal customPrincipal) {
		this.customPrincipal = customPrincipal;
	}

	@Override
	public Principal getUserPrincipal() {
		return customPrincipal;
	}

	@Override
	public boolean isUserInRole(String role) {
		return true;
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
	public String getAuthenticationScheme() {
		return null;
	}

}
