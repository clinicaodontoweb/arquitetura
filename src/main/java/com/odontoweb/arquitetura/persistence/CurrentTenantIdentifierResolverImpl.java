package com.odontoweb.arquitetura.persistence;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver{

	private static final String DEFAULT_TENANT_ID = "public";
	
	@Override
	public String resolveCurrentTenantIdentifier() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null) {
			String identifier = (String) requestAttributes.getAttribute("CURRENT_TENANT_IDENTIFIER",RequestAttributes.SCOPE_REQUEST);
			if (identifier != null) {
				return identifier;
			}
		}
		
		return DEFAULT_TENANT_ID;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
