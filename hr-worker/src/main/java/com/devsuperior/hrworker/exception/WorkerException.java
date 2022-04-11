package com.devsuperior.hrworker.exception;

public class WorkerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String RESOURCE_NOT_FOUND = "Resource not found : ";
	
	public WorkerException(Object msg) {
		super(RESOURCE_NOT_FOUND.concat(String.valueOf(msg)));
	}
}
