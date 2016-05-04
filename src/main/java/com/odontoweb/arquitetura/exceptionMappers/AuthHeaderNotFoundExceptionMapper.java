package com.odontoweb.arquitetura.exceptionMappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.odontoweb.arquitetura.exception.AuthHeaderNotFoundException;

public class AuthHeaderNotFoundExceptionMapper implements ExceptionMapper<AuthHeaderNotFoundException>{

	@Override
	public Response toResponse(AuthHeaderNotFoundException exception) {
		return Response.status(Status.BAD_REQUEST)
				.entity(exception.getMessage())
				.type(MediaType.TEXT_PLAIN)
				.build();
	}

}
