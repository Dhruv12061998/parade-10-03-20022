package com.uhcl.parade.dto;

import org.springframework.http.HttpStatus;

public class ParadeResponse {
	
	private Object data;
	private HttpStatus status;
	private String message;
	private String error;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
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
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public ParadeResponse(Object data, HttpStatus status, String message, String error) {
		super();
		this.data = data;
		this.status = status;
		this.message = message;
		this.error = error;
	}
	
	
	public ParadeResponse(Object data, HttpStatus status) {
		super();
		this.data = data;
		this.status = status;
		
	}
	
	public ParadeResponse(Object data, HttpStatus status, String message) {
		super();
		this.data = data;
		this.status = status;
		this.message = message;
		
	}
	
	public ParadeResponse(Object data) {
		super();
		this.data = data;
		this.status = HttpStatus.OK;
		this.message = "";
	}
	
	public ParadeResponse() {
		super();
	}
	
	
	
	
	
}
