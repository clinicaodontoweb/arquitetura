package com.odontoweb.arquitetura.model;

import java.io.Serializable;

public class ExceptionEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	private String mensagem;
	
	public ExceptionEntity() {}

	public ExceptionEntity(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
