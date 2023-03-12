package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.util.Constantes;

public class ErrorException extends RuntimeException {

	private static final long serialVersionUID = 1600537956145949351L;
	
	private String codeError;
	private String message;
	private HttpStatus status;
	
	public ErrorException() {	
		this.codeError=Constantes.COD_ERROR_500;
		this.message=Constantes.MENSAJE_ERROR_500;
		this.status=HttpStatus.INTERNAL_SERVER_ERROR;
	}
	public ErrorException(String codeError, String message,HttpStatus status) {
		this.message=message;
		this.codeError=codeError;
		this.status=status;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}



}
