package com.desafio.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.desafio.service.exception.CpfClientReadyRegisterException;
import com.desafio.service.exception.NotFoundCostumerException;
import com.desafio.service.exception.details.CpfClientReadyRegisterExceptionDetails;
import com.desafio.service.exception.details.ExceptionDetails;
import com.desafio.service.exception.details.NotFoundCostumerExceptionDetails;
import com.desafio.service.exception.details.ValidationExceptionDetails;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CpfClientReadyRegisterException.class)
	public ResponseEntity<CpfClientReadyRegisterExceptionDetails> handleCpfClientReadyRegisterException(CpfClientReadyRegisterException exception) {
		return new ResponseEntity<>(
				CpfClientReadyRegisterExceptionDetails.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.title("CPF Exception, Invalid Fields")
					.details(exception.getMessage())
					.developerMessage(exception.getClass().getName())
					.build(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundCostumerException.class)
	public ResponseEntity<NotFoundCostumerExceptionDetails> handleNotFoundException(NotFoundCostumerException exception) {
		return new ResponseEntity<>(
				NotFoundCostumerExceptionDetails.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.NOT_FOUND.value())
					.title("Not found exception")
					.details(exception.getMessage())
					.developerMessage(exception.getClass().getName())
					.build(), HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		List<String> fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.toList());
		List<String> fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
		
		return new ResponseEntity<>(
				ValidationExceptionDetails.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.title("Bad Request Exception, Invalid Fields")
					.details("Validation failed for argument!")
					.developerMessage(exception.getClass().getName())
					.fields(fields)
					.fieldsMessage(fieldsMessage)
					.build(), HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception exception, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionDetails exceptionDetails = ExceptionDetails.builder()
			.timestamp(LocalDateTime.now())
			.status(status.value())
			.title(exception.getCause().getMessage())
			.details(exception.getMessage())
			.developerMessage(exception.getClass().getName())
			.build();
		
		return new ResponseEntity<>(exceptionDetails, headers, status);
	}
}
