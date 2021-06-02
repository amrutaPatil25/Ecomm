package com.bank.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.demo.entity.Transaction;
import com.bank.demo.repository.TransactionRepository;
import com.bank.demo.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository tranRepository;
	@Override
	public void addTransaction(Transaction transaction) {
		tranRepository.save(transaction);
		
	}

	@Override
	public List<Transaction> getAllTransactionByAccountId(Integer accountIds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
