package com.bank.demo.exception;

public class InvalidAccountException extends RuntimeException{
	
	String message;
	public InvalidAccountException(String message)
	{
		this.message = message ;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
