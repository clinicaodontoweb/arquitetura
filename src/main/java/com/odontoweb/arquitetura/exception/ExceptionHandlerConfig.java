package com.odontoweb.arquitetura.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerConfig {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<String> handleErros(MethodArgumentNotValidException e){
		return new ResponseEntity<String>("Erros", HttpStatus.BAD_REQUEST);
	}
}
