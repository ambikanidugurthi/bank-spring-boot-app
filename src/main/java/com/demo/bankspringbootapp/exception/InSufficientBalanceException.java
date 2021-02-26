package com.demo.bankspringbootapp.exception;

public class InSufficientBalanceException extends Throwable{
	private String message;

	public InSufficientBalanceException() {
		
	}
	public InSufficientBalanceException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "InvalidDateRangeException [message=" + message + "]";
	}
	
	
}
