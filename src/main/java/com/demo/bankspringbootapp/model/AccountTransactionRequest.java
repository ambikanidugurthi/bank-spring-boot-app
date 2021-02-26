package com.demo.bankspringbootapp.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AccountTransactionRequest {

	private String accountNumber;
	private String transactionType;
	private Timestamp fromDate;
	private Timestamp toDate;
	 
	public AccountTransactionRequest() {
		super();
	}

	public AccountTransactionRequest(String accountNumber, String transactionType, Timestamp fromDate,
			Timestamp toDate) {
		super();
		this.accountNumber = accountNumber;
		this.transactionType = transactionType;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Timestamp getFromDate() {
		return fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	public Timestamp getToDate() {
		return toDate;
	}

	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
	}

}
