package com.bank.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.demo.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction	,Integer> {

	
	//List<Transaction> findByAccount(Integer accountId);
}
