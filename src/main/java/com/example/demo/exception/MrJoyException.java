package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class MrJoyException extends RuntimeException {
	
	private String codeError;
	private HttpStatus status;
	
	public MrJoyException(String codeError, String message) {
		super(message);
		this.codeError=codeError;
		this.status=HttpStatus.BAD_REQUEST;
	}

	public String getCodeError() {
		return codeError;
	}

	public void setCodeError(String codeError) {
		this.codeError = codeError;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
