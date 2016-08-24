package com.odontoweb.arquitetura.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.odontoweb.arquitetura.model.ValidationExceptionEntity;

@ControllerAdvice
public class BeanValidationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationExceptionEntity>> handleBeanValidation(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        
        return new ResponseEntity<List<ValidationExceptionEntity>>(processFieldErros(fieldErrors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	private List<ValidationExceptionEntity> processFieldErros(List<FieldError> fieldErrors){
		List<ValidationExceptionEntity> erros = new ArrayList<ValidationExceptionEntity>();
		fieldErrors.forEach(erro -> erros.add(new ValidationExceptionEntity(erro.getField(), erro.getDefaultMessage())));
		
		return erros;
	}
}
