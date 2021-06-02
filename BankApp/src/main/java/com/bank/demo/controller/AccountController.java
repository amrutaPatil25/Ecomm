package com.bank.demo.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.demo.dto.AccountRequestDto;
import com.bank.demo.entity.Account;
import com.bank.demo.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController
{
	
	@Autowired
	AccountService accService;
	
	@PostMapping
	public String addAccount(@RequestBody AccountRequestDto account)
	{
		Account acc = new Account();
		BeanUtils.copyProperties(account, acc);
		accService.addAccount(acc);
		return "Account Added";
	}
	
	@PutMapping
	public String fundTransfer(@Valid @Size(message="to account number required")@RequestParam Integer fromAccount,
			@Valid @Size(message="from account number required") @RequestParam Integer toAccount,
			@Valid @Size(message="amount required")@RequestParam double amount)
	{
	boolean flag =	accService.fundTransfer(fromAccount, toAccount, amount);
	if(flag)
	{
		return "successful";
	}
	return "Transaction failed";
		
	}
	
	@GetMapping
	public Account getAccountByAccountNumber(@RequestParam Integer accountNumber)
	{
		return accService.getAccountByAccountNumber(accountNumber);
	}
	
	

}
