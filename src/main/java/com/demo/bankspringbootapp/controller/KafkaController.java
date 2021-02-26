package com.demo.bankspringbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bankspringbootapp.model.AccountRequest;
import com.demo.bankspringbootapp.service.KafkaConsumerService;
import com.demo.bankspringbootapp.service.KafkaSenderService;

@RestController
public class KafkaController {

	@Autowired
	KafkaSenderService kafkaSenderService;
	
	@Autowired
	KafkaConsumerService KafkaConsumerService;
	
	/**
	 * 
	 * @param accountRequest
	 * @return
	 */
	@RequestMapping(value = "/kafkaAccountPublish", method = RequestMethod.POST)
	public String producerAccountDetail(@RequestBody AccountRequest accountRequest) {
		kafkaSenderService.publishAccountDetails(accountRequest);
		return "Message sent to the Kafka Topic account_topic Successfully";
	}
	
	/**
	 * 
	 * @param accountRequest
	 * @return
	 */
	@RequestMapping(value = "/kafkaAccountConsume", method = RequestMethod.POST)
	public String consumeAccountDetail(@RequestBody AccountRequest accountRequest) {
		 KafkaConsumerService.consumerAccountDetail(accountRequest);
		return "Data saved into Database";
	}
	
	
}
