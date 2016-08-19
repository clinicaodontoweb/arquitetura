package com.odontoweb.arquitetura.model;

public class User {
	
	private String username;
	private String tenant;
	private String roles;
	
	public User(String username, String tenant, String roles) {
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
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
}
