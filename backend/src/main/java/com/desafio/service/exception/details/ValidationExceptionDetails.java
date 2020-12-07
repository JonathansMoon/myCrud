package com.desafio.service.exception.details;

import java.util.List;

import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
	private final List<String> fields;
	private final List<String> fieldsMessage;
}
