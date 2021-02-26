package com.demo.bankspringbootapp.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.bankspringbootapp.entity.Account;
import com.demo.bankspringbootapp.exception.AccountNotFoundException;

//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class BIANControllerTest extends AbstractTest{

	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test 
	public void getAccountDetailsTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/accounts")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.accounts").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.accounts[*].accountId")
						.isNotEmpty());
				
	}
	 
	 
	 
	@Test
	public void getAccountDetailForAGivenAccountNumberTest() throws Exception {
		String uri = "/getAccount/11111";
		MvcResult mvcResult =  this.mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		Account acc = super.mapFromJson(content,Account.class);
		assertEquals("11111", acc.getAccountNumber());
		
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void throwExceptionForAccountNumberZero() throws AccountNotFoundException {
		String uri = "/getAccount/0";
		this.mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	}
	
	
	@Test
	public void createAccount() throws Exception {
		String uri = "/getAccount/11111";
		
		Account account = new Account();
		account.setAccountId(22222);
		account.setBalance(new BigDecimal(10000));
		
		String json = super.mapToJson(account);
		
		MvcResult mvcResult =  this.mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		
	}
}
