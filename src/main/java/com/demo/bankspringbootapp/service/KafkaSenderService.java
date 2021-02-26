package com.demo.bankspringbootapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.bankspringbootapp.model.AccountRequest;
import com.demo.bankspringbootapp.model.AccountTransactionRequest;

@Service
public class KafkaSenderService {
	
	@Autowired
	private KafkaTemplate<String, AccountRequest> accountRequestKafkaTemplate;
	

	@Autowired
	private KafkaTemplate<String, AccountTransactionRequest> AccountTransactionRequestKafkaTemplate;
	
	String kafkaTopic = "account_topic";
	
	public void publishAccountDetails(AccountRequest accountRequest) {
		accountRequestKafkaTemplate.send(kafkaTopic, accountRequest);
	}
	
	public void publishAccountTransactionDetails(AccountTransactionRequest accountTransactionRequest) {
		AccountTransactionRequestKafkaTemplate.send(kafkaTopic,accountTransactionRequest);
	}

}
