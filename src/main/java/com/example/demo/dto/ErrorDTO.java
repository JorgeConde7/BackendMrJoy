package com.example.demo.dto;


public class ErrorDTO {

	private String code;
	private String message;
	
	public ErrorDTO() {
		
	}
	
	public ErrorDTO(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
