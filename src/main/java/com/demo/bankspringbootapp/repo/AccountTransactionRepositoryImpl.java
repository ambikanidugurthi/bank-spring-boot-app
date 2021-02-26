package com.demo.bankspringbootapp.repo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query ;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.bankspringbootapp.entity.Account;
import com.demo.bankspringbootapp.entity.AccountTransaction;
import com.demo.bankspringbootapp.exception.AccountNotFoundException;
import com.demo.bankspringbootapp.exception.InSufficientBalanceException;

@Repository
@Transactional
public class AccountTransactionRepositoryImpl {

	 @PersistenceContext
	 private EntityManager em;
	 
	 @Autowired
	 AccountRepo accountRepo;
	 
	 /**
	  * 
	  * @param accountTransaction
	  * @throws InSufficientBalanceException
	  */
	 @Transactional(rollbackOn = AccountNotFoundException.class)
	 public void save(AccountTransaction accountTransaction) throws InSufficientBalanceException {		
		
		Account account = accountRepo.findByAccountNumber(accountTransaction.getAccountNumber());
		         
		if(account == null) {
			throw new AccountNotFoundException();
		}
		BigDecimal existingBal = null;
		BigDecimal newBal = null;
		if(account != null) {
			existingBal = account.getBalance();
		}
		/*if(null == account && accountTransaction.getTransactionType().equals(com.demo.bankspringbootapp.utility.Type.DEPOSIT.toString())) {
			//assuming - create account with deposited amount  assuming account not created - which is not the real case 
		    Account newAccount = new Account();
		    newAccount.setAccountNumber(accountTransaction.getAccountNumber());
		    newAccount.setBalance(accountTransaction.getTransactionAmount());
		    newAccount.setLastupdatedTimeStamp(new Timestamp(System.currentTimeMillis()));
			accountRepo.save(newAccount);
		}*/
		if(null != account) {
			if(accountTransaction.getTransactionType().equals(com.demo.bankspringbootapp.utility.Type.WITHDRAW.toString())) {
				if(existingBal.compareTo(accountTransaction.getTransactionAmount()) == -1) {
					throw new InSufficientBalanceException("Insufficient Balance in  your account "+ accountTransaction.getAccountNumber());
				}
				em.persist(accountTransaction);
				newBal = existingBal.subtract(accountTransaction.getTransactionAmount());
				System.out.println("new bal after withdrawal "+ newBal);
				updateaccountBalance(newBal,accountTransaction.getAccountNumber());				
			}
			if(accountTransaction.getTransactionType().equals(com.demo.bankspringbootapp.utility.Type.DEPOSIT.toString())) {
				em.persist(accountTransaction);
				newBal = existingBal.add(accountTransaction.getTransactionAmount());
				System.out.println("new bal after deposit "+ newBal);
				updateaccountBalance(newBal,accountTransaction.getAccountNumber());
			}
		}		
	 }

	 /**
	  * Updating existing balance
	  * @param balance
	  * @param accountNumber
	  */
	 public void updateaccountBalance(BigDecimal balance , String accountNumber) {
		 Account account = accountRepo.findByAccountNumber(accountNumber);
		 account.setBalance(balance);
		 account.setLastupdatedTimeStamp(new Timestamp(System.currentTimeMillis()) );
		 
	 }
	 
	 /**
	  * 
	  * @param accountNumber
	  * @return
	  */
	public List<AccountTransaction> findByAccountNumber(String accountNumber) {
		Query query = em.createQuery("from AccountTransaction a where a.accountNumber = :accountNumber");
		query.setParameter("accountNumber", accountNumber);
		List list = query.getResultList();
		return list;
	}

	/**
	 * 
	 * @param accountNumber
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<AccountTransaction> findByAccountNumberAndDate(String accountNumber, Date fromDate, Date toDate) {
		Query query = em.createQuery("from AccountTransaction t where t.accountNumber = :accountNumber and   t.transactionDate >= :fromDate and t.transactionDate <= :toDate");
		query.setParameter("accountNumber", accountNumber);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		List list = query.getResultList();
		return list;
	}
	
	/**
	 * 
	 * @param accountNumber
	 * @param fromDate
	 * @param toDate
	 * @param transactionType
	 * @return
	 */
	public List<AccountTransaction> findByAccountNumberAndDate(String accountNumber, Date fromDate, Date toDate, String transactionType) {
		String sql = "from AccountTransaction t where t.accountNumber = :accountNumber and   t.transactionDate >= :fromDate and t.transactionDate <=: toDate and transactionType = :transactionType";
				Query query = em.createQuery(sql);
		query.setParameter("accountNumber", accountNumber);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		query.setParameter("transactionType", transactionType);
		List list = query.getResultList();
		return list;
	}

}
