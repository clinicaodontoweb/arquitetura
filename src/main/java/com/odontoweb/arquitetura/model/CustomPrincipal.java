package com.odontoweb.arquitetura.model;

import java.security.Principal;

public class CustomPrincipal implements Principal{

	private User user;
	
	public CustomPrincipal(User user) {
		this.user = user;
	}

	@Override
	public String getName() {
		return user.getUsername();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
