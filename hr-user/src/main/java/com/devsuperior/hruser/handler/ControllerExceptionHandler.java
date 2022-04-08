package com.devsuperior.hruser.handler;

import java.time.Instant;

import java.time.Instant;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devsuperior.hruser.exception.UserException;

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	private static final String RECURSO_NAO_ENCONTRADO = "Recurso não encontrado";

	@ExceptionHandler(UserException.class)
	public ResponseEntity<StandardError> userNotFound(UserException e, HttpServletRequest request){
		String error = RECURSO_NAO_ENCONTRADO;
		HttpStatus status = HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status).body(new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI()));
	}

}
