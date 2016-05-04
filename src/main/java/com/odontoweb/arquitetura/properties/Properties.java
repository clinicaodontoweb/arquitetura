package com.odontoweb.arquitetura.properties;

import org.springframework.beans.factory.annotation.Value;

public class Properties {

	@Value("${authorization.key}")
	private String key;
	
	public Properties() {}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
