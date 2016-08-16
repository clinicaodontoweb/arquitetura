package com.odontoweb.arquitetura.filter;

import io.jsonwebtoken.Claims;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odontoweb.arquitetura.authorization.JWTAuthorizationUtil;
import com.odontoweb.arquitetura.exception.AuthHeaderNotFoundException;
import com.odontoweb.arquitetura.model.ExceptionEntity;
import com.odontoweb.arquitetura.model.User;

public class AuthorizationFilter implements Filter{

	private JWTAuthorizationUtil authorizationUtil;
	
	@Autowired
	public AuthorizationFilter(JWTAuthorizationUtil authorizationUtil) {
		this.authorizationUtil = authorizationUtil;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req 	=	(HttpServletRequest) request;
		HttpServletResponse res	=	(HttpServletResponse) response;
		
		try {
			Claims claims = authorizationUtil.validaToken(req.getHeader("Authorization"));
			User user = new User(claims.getSubject(), (String)claims.get("tenant"));
			RequestContextHolder.currentRequestAttributes().setAttribute("CURRENT_TENANT_IDENTIFIER", user.getTenant(), RequestAttributes.SCOPE_REQUEST);
			
			chain.doFilter(request, response);
			
		} catch (AuthHeaderNotFoundException e) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			res.setContentType("application/json");
        	res.getWriter().printf(new ObjectMapper().writeValueAsString(new ExceptionEntity(e.getMessage())));
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void destroy() {}

}
