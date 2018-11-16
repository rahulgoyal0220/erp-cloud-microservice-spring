package com.spm.erp.exception;

public class CustomException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
	
	public CustomException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CustomException [message=" + message + "]";
	}

}
