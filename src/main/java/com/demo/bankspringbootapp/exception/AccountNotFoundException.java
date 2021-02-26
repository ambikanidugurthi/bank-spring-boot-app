package com.demo.bankspringbootapp.exception;

public class AccountNotFoundException extends RuntimeException{

	private String message;

	public AccountNotFoundException() {
		super();
	}

	public AccountNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "AccountNotFoundException [message=" + message + "]";
	}
	
	
}
