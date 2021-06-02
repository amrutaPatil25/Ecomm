package com.bank.demo.exception;

public class InsufficientBalanceException extends RuntimeException{
	
	String message;
	public InsufficientBalanceException(String message)
	{
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
