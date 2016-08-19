package com.odontoweb.arquitetura.security;

import io.jsonwebtoken.Claims;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.odontoweb.arquitetura.model.User;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter{

	@Autowired
	private JWTAuthorizationUtil jwtUtil;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		String authToken = httpRequest.getHeader("X-Auth-Token");
		
		if(authToken != null && authToken != "" && SecurityContextHolder.getContext().getAuthentication() == null){
			Claims claims = jwtUtil.getClaimsFromToken(authToken);
			User user = new User(claims.getSubject(), claims.get("tenant", String.class), claims.get("roles", String.class));
			RequestContextHolder.currentRequestAttributes().setAttribute("CURRENT_TENANT_IDENTIFIER", user.getTenant(), RequestAttributes.SCOPE_REQUEST);
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles())));
		}
		
		chain.doFilter(req, res);
	}
}
