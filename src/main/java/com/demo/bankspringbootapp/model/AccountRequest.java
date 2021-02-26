package com.demo.bankspringbootapp.model;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class AccountRequest {
	
	private String accountNo;
	private BigDecimal balance;
	private Timestamp lastupdated;
	
	public AccountRequest() {
		super();
	}
	
	public AccountRequest(String accountNo, BigDecimal balance, Timestamp lastupdated) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		this.lastupdated = lastupdated;
	}


	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Timestamp getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}
	@Override
	public String toString() {
		return "AccountRequestTO [accountNo=" + accountNo + ", balance=" + balance + ", lastupdated=" + lastupdated
				+ "]";
	}
	
}
