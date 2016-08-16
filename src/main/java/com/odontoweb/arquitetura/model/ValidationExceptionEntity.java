package com.odontoweb.arquitetura.model;

public class ValidationExceptionEntity {

	private String propriedade;
	private String mensagem;
	
	public ValidationExceptionEntity() {}

	public ValidationExceptionEntity(String propriedade, String mensagem) {
		this.propriedade = propriedade;
		this.mensagem = mensagem;
	}

	public String getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
