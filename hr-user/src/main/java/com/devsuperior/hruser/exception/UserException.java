package com.devsuperior.hruser.exception;

public class UserException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private static final String RESOURCE_NOT_FOUND = "Resource not found : ";
	
	public UserException(Object msg) {
		super(RESOURCE_NOT_FOUND.concat(String.valueOf(msg)));
	}
}
