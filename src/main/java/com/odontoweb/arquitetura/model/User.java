package com.odontoweb.arquitetura.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private String username;
	private String tenant;
	private List<String> roles;
	
	public User(String username, String tenant, List<String> roles) {
		this.username = username;
		this.tenant = tenant;
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
