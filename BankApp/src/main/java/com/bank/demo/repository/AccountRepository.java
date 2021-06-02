package com.bank.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.demo.entity.Account;
import com.bank.demo.service.AccountService;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	Account findByAccountNo(Integer accountNo);
	

}
