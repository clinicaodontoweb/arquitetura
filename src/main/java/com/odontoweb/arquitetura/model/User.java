package com.odontoweb.arquitetura.model;

public class User {
	
	private String username;
	private String tenant;
	
	public User(String username, String tenant) {
		this.username = username;
		this.tenant = tenant;
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
	
}
