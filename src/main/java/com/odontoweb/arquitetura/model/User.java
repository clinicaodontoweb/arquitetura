package com.odontoweb.arquitetura.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private String username;
	private String tenant;
	private String tipoUsuario;
	private Boolean isAdmin;
	private List<String> roles;

	public User(String username, String tenant, String tipoUsuario, Boolean isAdmin, List<String> roles) {
		this.username = username;
		this.tenant = tenant;
		this.tipoUsuario = tipoUsuario;
		this.isAdmin = isAdmin;
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

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;

		User user = (User) object;

		if (!tenant.equals(user.tenant)) return false;

		return true;
	}

	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + tenant.hashCode();
		return result;
	}
}
