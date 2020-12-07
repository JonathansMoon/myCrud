package com.desafio.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundCostumerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundCostumerException(String message) {
		super(message);
	}

}
