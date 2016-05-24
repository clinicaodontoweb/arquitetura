package com.odontoweb.arquitetura.filter;

import io.jsonwebtoken.Claims;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.odontoweb.arquitetura.model.CustomPrincipal;
import com.odontoweb.arquitetura.model.CustomSecurityContext;
import com.odontoweb.arquitetura.model.User;
import com.odontoweb.arquitetura.utils.AuthorizationUtils;

public class AuthorizationFilter implements ContainerRequestFilter{

	@Autowired AuthorizationUtils authorizationUtils;
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		
		Claims claims = authorizationUtils.validaToken(requestContext.getHeaderString("Authorization"));
		User user = new User(claims.getSubject(), (String)claims.get("tenant"));
		
		RequestContextHolder.currentRequestAttributes().setAttribute("CURRENT_TENANT_IDENTIFIER", user.getTenant(), RequestAttributes.SCOPE_REQUEST);
		requestContext.setSecurityContext(new CustomSecurityContext(new CustomPrincipal(user)));
	}
	
	

}
