package com.demo.bankspringbootapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.bankspringbootapp.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>{
	Account findByAccountNumber(String accountNumber);
}
