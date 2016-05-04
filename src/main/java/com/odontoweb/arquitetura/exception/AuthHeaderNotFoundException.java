package com.odontoweb.arquitetura.exception;

public class AuthHeaderNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public AuthHeaderNotFoundException() {
		super("Authorization header not found");
	}
	
	public AuthHeaderNotFoundException(String msg){
		super(msg);
	}
}
