package com.odontoweb.arquitetura.authorization;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.odontoweb.arquitetura.exception.AuthHeaderNotFoundException;
import com.odontoweb.arquitetura.model.User;

public class JWTAuthorizationUtil {

	private String key;

    public JWTAuthorizationUtil(String key) {
        this.key = key;
    }

    public String build(User user) {
        return Jwts.builder().setHeaderParam("type", "JWT")
                .setSubject(user.getUsername())
                .claim("tenant", user.getTenant())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
    
    public Claims validaToken(String authorizationHeader) throws AuthHeaderNotFoundException{
		
		if(!isBearerToken(authorizationHeader)) throw new AuthHeaderNotFoundException();
		String token =  getToken(authorizationHeader);
		Claims claims = getClaims(token);
		validaClaims(claims);
		
		return claims;
	}

	public String buildToken(User user){
		return new JWTAuthorizationUtil(key).build(user);
	}
	
	private void validaClaims(Claims claims) throws AuthHeaderNotFoundException{
		if(claims.getSubject() == null || claims.getSubject() == "") throw new AuthHeaderNotFoundException("Invalid token");
		if((String)claims.get("tenant") == null || (String)claims.get("tenant") == "") throw new AuthHeaderNotFoundException("Invalid token, tenant not found");
		if(claims.getIssuedAt() == null) throw new AuthHeaderNotFoundException("Invalid token, expire date not found");
		if(!validateExpireDate(claims.getIssuedAt())) throw new AuthHeaderNotFoundException("Token has expired");
	}
	
	private Claims getClaims(String token) throws AuthHeaderNotFoundException{
		Claims claims = null;
		try {
			claims = new JWTAuthorizationUtil(key).parse(token);
		} catch (Exception e) {
			throw new AuthHeaderNotFoundException("Invalid token, cannot read token");
		}
		
		return claims;
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
