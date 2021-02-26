package com.demo.bankspringbootapp;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.bankspringbootapp.entity.AccountTransaction;
import com.demo.bankspringbootapp.exception.InSufficientBalanceException;
import com.demo.bankspringbootapp.repo.AccountTransactionRepositoryImpl;

//@EnableJpaRepositories		
@SpringBootApplication
public class BankSpringBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSpringBootAppApplication.class, args);
	}

	/*@Bean
    CommandLineRunner initDatabase(AccountRepo repository) {
        return args -> {
            repository.save(new Account("11111", new BigDecimal("1000"), new Timestamp(System.currentTimeMillis()) ));
            repository.save(new Account("22222", new BigDecimal("100"), new Timestamp(System.currentTimeMillis()) ));
            repository.save(new Account("33333", new BigDecimal("100"), new Timestamp(System.currentTimeMillis()) ));
        };
    }*/
	
	@Bean
    CommandLineRunner initDatabase(AccountTransactionRepositoryImpl repository) {
        return args -> { 
            try {
				repository.save(new AccountTransaction("11111", "DEPOSIT",  new Timestamp(System.currentTimeMillis()) ,new BigDecimal("1000")));
				repository.save(new AccountTransaction("22222", "DEPOSIT",  new Timestamp(System.currentTimeMillis()) ,new BigDecimal("100")));
	            repository.save(new AccountTransaction("33333", "DEPOSIT",  new Timestamp(System.currentTimeMillis()),new BigDecimal("100") ));
	            repository.save(new AccountTransaction("33333", "WITHDRAW", new Timestamp(System.currentTimeMillis()),new BigDecimal("100") ));
	            repository.save(new AccountTransaction("11111", "WITHDRAW", new Timestamp(System.currentTimeMillis()),new BigDecimal("50") ));
	            repository.save(new AccountTransaction("11111", "WITHDRAW", new Timestamp(System.currentTimeMillis()),new BigDecimal("25") ));
			} catch (InSufficientBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
        };
    }
	 
	
}
