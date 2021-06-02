package com.bank.demo.service;

import java.util.List;

import com.bank.demo.entity.Transaction;

public interface TransactionService {

	
	public void addTransaction(Transaction transaction);
	
	public List<Transaction> getAllTransactionByAccountId(Integer accountId);
}
