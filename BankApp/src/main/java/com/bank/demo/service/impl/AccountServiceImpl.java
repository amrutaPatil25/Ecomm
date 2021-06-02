package com.bank.demo.service.impl;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.demo.entity.Account;
import com.bank.demo.entity.Transaction;
import com.bank.demo.exception.InsufficientBalanceException;
import com.bank.demo.exception.InvalidAccountException;
import com.bank.demo.repository.AccountRepository;
import com.bank.demo.service.AccountService;
import com.bank.demo.service.TransactionService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accRepository;
	
	@Autowired
	TransactionService transactionService;
	
	Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Override
	public String addAccount(Account account) {
		// TODO Auto-generated method stub
		accRepository.save(account);
		return "Account added";
	}

	@Override
	public boolean fundTransfer(Integer fromAccountNo, Integer toAccountNo, double amount) {
	
		Account fromAcc = getAccountByAccountNumber(fromAccountNo);
		Account toAcc = getAccountByAccountNumber(toAccountNo);
		if(fromAcc==null)
		{
			throw new InvalidAccountException("Account number Not found");
		}
		
		double fromAccBalance = fromAcc.getBalance();
		double toAccBalance = toAcc.getBalance();
		
		if(fromAccBalance<=amount)
		{
			throw new InsufficientBalanceException("insufficient Balance");
		}
		
		fromAccBalance = fromAccBalance - amount ; 
		toAccBalance = toAccBalance + amount ;
		
		fromAcc.setBalance(fromAccBalance);
		toAcc.setBalance(toAccBalance);
		
		accRepository.save(fromAcc);
		accRepository.save(toAcc);
		
		Transaction transaction = new Transaction();
		transaction.setAccount(fromAcc);
		transaction.setAmount(amount);
		transaction.setTransactionDate(new Date(new java.util.Date().getTime()));
		transaction.setToAccNo(toAccountNo);
		transaction.setTransactionType("Debit");
		transactionService.addTransaction(transaction);
		
		transaction = new Transaction();
		transaction.setAccount(toAcc);
		transaction.setAmount(amount);
		transaction.setTransactionDate(new Date(new java.util.Date().getTime()));
		transaction.setToAccNo(fromAccountNo);
		transaction.setTransactionType("credit");
		transactionService.addTransaction(transaction);
		
		
	
		
		return true;
	}
	
	

	@Override
	public Account getAccountByAccountNumber(Integer accountNumber) {
		// TODO Auto-generated method stub
		return accRepository.findByAccountNo(accountNumber);
	}

	@Override
	public int updateAccount(Integer accountId, Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

}
