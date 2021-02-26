package com.demo.bankspringbootapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.demo.bankspringbootapp.entity.Account;
import com.demo.bankspringbootapp.entity.AccountTransaction;
import com.demo.bankspringbootapp.model.AccountRequest;
import com.demo.bankspringbootapp.model.AccountTransactionRequest;
import com.demo.bankspringbootapp.repo.AccountRepo;
import com.demo.bankspringbootapp.repo.AccountTransactionRepositoryImpl;

@Service
public class KafkaConsumerService {

	@Autowired
	AccountRepo repo;
	
	@Autowired
	AccountTransactionRepositoryImpl AccountTransactionRepositoryImpl;
	
	@KafkaListener(topics = "account_topic", groupId="group")
	public void consumerAccountDetail(AccountRequest accountRequest) {
		Account account = new Account();
		account.setAccountNumber(accountRequest.getAccountNo());
		account.setBalance(accountRequest.getBalance());
		account.setLastupdatedTimeStamp(accountRequest.getLastupdated());
		repo.save(account);
	}
	
}
