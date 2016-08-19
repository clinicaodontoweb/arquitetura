package com.odontoweb.arquitetura.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.odontoweb.arquitetura.exception.AuthHeaderNotFoundException;
import com.odontoweb.arquitetura.model.User;

public class JWTAuthorizationUtil {

	private String key;

    public JWTAuthorizationUtil() {
        this.key = "chavesecreta";
    }

    public String build(User user) {
        return Jwts.builder().setHeaderParam("type", "JWT")
                .setSubject(user.getUsername())
                .claim("tenant", "tenant-" + user.getTenant())
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.encode(key.getBytes()))
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(key.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
    
    public Claims getClaimsFromToken(String token) throws AuthHeaderNotFoundException{
		Claims claims = getClaims(token);
		validaClaims(claims);
		
		return claims;
	}

	public String buildToken(User user){
		return new JWTAuthorizationUtil().build(user);
	}
	
	private void validaClaims(Claims claims) throws AuthHeaderNotFoundException{
		if(claims.getSubject() == null || claims.getSubject() == "") throw new AuthHeaderNotFoundException("Invalid token");
		if((String)claims.get("tenant") == null || (String)claims.get("tenant") == "") throw new AuthHeaderNotFoundException("Invalid token, tenant not found");
		if((String)claims.get("roles") == null || (String)claims.get("roles") == "") throw new AuthHeaderNotFoundException("Invalid token, roles not found");
		if(claims.getIssuedAt() == null) throw new AuthHeaderNotFoundException("Invalid token, expire date not found");
		if(!validateExpireDate(claims.getIssuedAt())) throw new AuthHeaderNotFoundException("Token has expired");
	}
	
	private Claims getClaims(String token) throws AuthHeaderNotFoundException{
		Claims claims = null;
		try {
			claims = new JWTAuthorizationUtil().parse(token);
		} catch (Exception e) {
			throw new AuthHeaderNotFoundException("Invalid token, cannot read token");
		}
		
		return claims;
	}

	private boolean validateExpireDate(Date date){
		return LocalDateTime.from(date.toInstant().atZone(ZoneId.of("UTC"))).plusMonths(1).isAfter(LocalDateTime.now());
	}
	
	public static void main(String[] args) {
		String token = Jwts.builder().setHeaderParam("type", "JWT")
                .setSubject("andre")
                .claim("tenant", "tenant-andre")
                .claim("roles", "admin")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "f113ad198457d364d9aec6dc5433b52a")
                .compact();
		
		System.out.println(token);
	}
	
}
