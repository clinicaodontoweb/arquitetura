package com.odontoweb.arquitetura.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import com.odontoweb.arquitetura.model.User;

public class JWTUtils {

	private String key;

    public JWTUtils(String key) {
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
}
