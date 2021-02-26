package com.demo.bankspringbootapp.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.demo.bankspringbootapp.entity.Account;
import com.demo.bankspringbootapp.entity.AccountTransaction;
import com.demo.bankspringbootapp.exception.AccountNotFoundException;
import com.demo.bankspringbootapp.exception.InSufficientBalanceException;
import com.demo.bankspringbootapp.exception.InvalidDateRangeException;
import com.demo.bankspringbootapp.model.AccountTransactionRequest;
import com.demo.bankspringbootapp.repo.AccountRepo;
import com.demo.bankspringbootapp.repo.AccountTransactionRepositoryImpl;
import com.demo.bankspringbootapp.service.BIANService;

@RestController
public class BIANController {	 
	

	@Autowired
	private AccountRepo accountReposiotry;
	
	@Autowired
	private BIANService bianService;
	
	@Autowired
	private AccountTransactionRepositoryImpl accountTransactionRepo;
	
	@GetMapping("/accounts")
	public List<Account> getAccountDetails() {
		List<Account> accounts  = accountReposiotry.findAll();
		return accounts;
	}
	
	@RequestMapping(value="/getAccount/create",method = RequestMethod.POST)
	public void createAccount(@RequestBody Account account) {
		accountReposiotry.save(account);
	}
	
	@RequestMapping(value = "/getAccount/{accountNo}", method = RequestMethod.GET)
	public Account getAccountDetail(@PathVariable String accountNo, HttpServletResponse response) {
		Account account  = bianService.getAccountDetail(accountNo);
		return account;
	}
	
	@RequestMapping(value="/createTransaction",method = RequestMethod.POST)
	 public void createTransaction(@RequestBody AccountTransaction accountTransaction) throws InSufficientBalanceException {
		if(null != accountTransaction.getAccountNumber() && null != accountTransaction.getTransactionType()) {
			accountTransactionRepo.save(accountTransaction);	
		}
		
	}
	
	@RequestMapping(value = "/getAccountTransactions/{accountNo}", method = RequestMethod.GET)
	public List<AccountTransaction> getAccountTransactions(@PathVariable String accountNo) {
		return accountTransactionRepo.findByAccountNumber(accountNo);
	}
	
	@RequestMapping(value = "/getAccountTransactions", method = RequestMethod.POST)
	public List getAccountTransactionsByDate(@RequestBody AccountTransactionRequest request) throws InvalidDateRangeException {
		List list = null;
		if(request != null ) {
			if(request.getFromDate().before(request.getToDate()) && request.getToDate().after(request.getFromDate())) {
				if(request.getTransactionType() == null) {
					list =  accountTransactionRepo.findByAccountNumberAndDate(request.getAccountNumber(),request.getFromDate(),request.getToDate());
				}else{
					list =  accountTransactionRepo.findByAccountNumberAndDate(request.getAccountNumber(),request.getFromDate(),request.getToDate(),request.getTransactionType());
				}
			}else {
				throw new InvalidDateRangeException("Please enter valid date range");
			}
			
		}
		return list;
	}
	 
}
