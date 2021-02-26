package com.demo.bankspringbootapp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id	
	@Basic(optional=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="accountId") 
	private Integer accountId;
	
	@Column(name="account_number")
	private String accountNumber;
	
	@Column(name="balance")
	private BigDecimal balance;
	
	@Column(name="last_updated_timeStamp")
	private Timestamp lastupdatedTimeStamp;

    /*@OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<AccountTransaction> accountTransaction = new ArrayList<>();*/
	
	public Account() {
		super();
	}

	public Account(String accountNumber, BigDecimal balance, Timestamp lastupdatedTimeStamp) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.lastupdatedTimeStamp = lastupdatedTimeStamp;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Timestamp getLastupdatedTimeStamp() {
		return lastupdatedTimeStamp;
	}

	public void setLastupdatedTimeStamp(Timestamp lastupdatedTimeStamp) {
		this.lastupdatedTimeStamp = lastupdatedTimeStamp;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", lastupdatedTimeStamp="
				+ lastupdatedTimeStamp + "]";
	}
	
}
