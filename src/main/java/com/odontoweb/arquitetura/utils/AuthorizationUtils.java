package com.odontoweb.arquitetura.utils;

import io.jsonwebtoken.Claims;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.odontoweb.arquitetura.exception.AuthHeaderNotFoundException;
import com.odontoweb.arquitetura.model.User;

public class AuthorizationUtils {

	private String key;
	
	public AuthorizationUtils(String key) {
		this.key = key;
	}
	
	public Claims validaToken(String authorizationHeader){
		
		if(!isBearerToken(authorizationHeader)) throw new AuthHeaderNotFoundException();
		String token =  getToken(authorizationHeader);
		Claims claims = getClaims(token);
		validaClaims(claims);
		
		return claims;
	}

	public String buildToken(User user){
		return new JWTUtils(key).build(user);
	}
	
	private void validaClaims(Claims claims){
		if(claims.getSubject() == null || claims.getSubject() == "") throw new AuthHeaderNotFoundException("Token inválido");
		if((String)claims.get("tenant") == null || (String)claims.get("tenant") == "") throw new AuthHeaderNotFoundException("Token inválido, tenant not found");
		if(claims.getIssuedAt() == null) throw new AuthHeaderNotFoundException("Token inválido, expire date not found");
		if(!validateExpireDate(claims.getIssuedAt())) throw new AuthHeaderNotFoundException("Token has expired");
	}
	
	private Claims getClaims(String token){
		return new JWTUtils(key).parse(token);
	}

	private boolean isBearerToken(String authHeader) {
        return authHeader != null && !authHeader.isEmpty() && authHeader.split(" ").length == 2 && authHeader.split(" ")[0].equals("Bearer");
    }
	
	private String getToken(String authHeader){
		return authHeader.split(" ")[1];
	}
	
	private boolean validateExpireDate(Date date){
		return LocalDateTime.from(date.toInstant().atZone(ZoneId.of("UTC"))).plusMonths(1).isAfter(LocalDateTime.now());
	}
	
}
