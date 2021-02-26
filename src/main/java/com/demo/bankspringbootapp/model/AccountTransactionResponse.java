package com.demo.bankspringbootapp.model;

import java.math.BigDecimal;
import java.util.List;

import com.demo.bankspringbootapp.entity.AccountTransaction; 

public class AccountTransactionResponse {

	private BigDecimal balance;
	private List<AccountTransaction> transactionDate;

	public void AccountTransactionResponse(){
	}
	
	public AccountTransactionResponse(BigDecimal balance, List<AccountTransaction> transactionDate) {
		super();
		this.balance = balance;
		this.transactionDate = transactionDate;
	}	
	
	
}
