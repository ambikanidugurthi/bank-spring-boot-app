package com.demo.bankspringbootapp.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.bankspringbootapp.entity.Account;
import com.demo.bankspringbootapp.repo.AccountRepo;

@Service
public class BIANService {

	@Autowired
	private AccountRepo accountReposiotry;
		
	public Account getAccountDetail(@PathVariable String accountNo) {
		Account account = accountReposiotry.findByAccountNumber(accountNo);
		if(account == null) {
			throw new EntityNotFoundException("Account Not Found for account No. : "+accountNo);
		}
		return account;
	}
}
