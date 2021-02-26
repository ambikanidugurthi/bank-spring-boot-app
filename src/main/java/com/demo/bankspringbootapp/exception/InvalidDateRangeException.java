package com.demo.bankspringbootapp.exception;

public class InvalidDateRangeException extends Exception{

	private String message;

	public InvalidDateRangeException() {
		
	}
	public InvalidDateRangeException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String toString() {
		return "InvalidDateRangeException [message=" + message + "]";
	}
	
	
	
	
}
