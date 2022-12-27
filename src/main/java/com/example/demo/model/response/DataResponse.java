package com.example.demo.model.response;

public class DataResponse<T> {

	
	private T data;
	private String message;
	private int status;
	
	public DataResponse() {
		super();
	}
	
	public DataResponse(T data, String message, int status) {
		super();
		this.data = data;
		this.message = message;
		this.status = status;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
