package com.bank.demo.service;

import com.bank.demo.entity.Account;

public interface AccountService 
{
	
	public String addAccount(Account account);
	
	public boolean fundTransfer(Integer fromAccountm,Integer toAccount,double amount);
	
	public Account getAccountByAccountNumber(Integer accountNumber);
	
	public int updateAccount(Integer accountId, Account account);

}
