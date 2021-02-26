package com.demo.bankspringbootapp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account_transaction")
public class AccountTransaction {

	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="transaction_id") 
	private Integer transactionId;
	
	@Column(name="account_number")
	private String accountNumber;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="transaction_date")
	private Timestamp transactionDate;
	
	@Column(name="transaction_amount")
	private BigDecimal transactionAmount;

	public AccountTransaction() {
		super();
	}

	public AccountTransaction(String accountNo, String transactionType, Timestamp transactionDate,
			BigDecimal transactionAmount) {
		super();
		this.accountNumber = accountNo;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNo) {
		this.accountNumber = accountNo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	@Override
	public String toString() {
		return "AccountTransaction [accountNumber=" + accountNumber + ", transactionType=" + transactionType
				+ ", transactionDate=" + transactionDate + ", transactionAmount=" + transactionAmount + "]";
	}
	
}
