package com.odontoweb.arquitetura.exception.response;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 892614493069659047L;

	private String message;
	private Throwable stacktrace;
	private Integer status;

	public ExceptionResponse(String message, Integer status) {
		this.message = message;
		this.status = status;
	}

	public ExceptionResponse(String message, Integer status, Throwable stacktrace) {
		this.message = message;
		this.status = status;
		this.stacktrace = stacktrace;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(Throwable stacktrace) {
		this.stacktrace = stacktrace;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
