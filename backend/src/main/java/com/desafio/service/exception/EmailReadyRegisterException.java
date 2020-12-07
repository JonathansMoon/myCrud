package com.desafio.service.exception;

public class EmailReadyRegisterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailReadyRegisterException(String message) {
		super(message);
	}
}
