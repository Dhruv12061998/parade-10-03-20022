package com.uhcl.parade.exception;

public class FileNotFoundException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FileNotFoundException(String message) {
		super();
		this.message = message;
	}

	public FileNotFoundException() {
		super();
	}
		
}
