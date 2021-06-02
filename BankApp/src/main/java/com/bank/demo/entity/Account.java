package com.bank.demo.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table
public class Account {
	
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name="gen",strategy = "foreign",
	parameters = @Parameter(name="property",value="user"))
	private int userId;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;

	private Integer accountNo;
	
	private double balance;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;
	
	@OneToMany(mappedBy = "account")
	private Set<Transaction> transactions;
	
	public Account()
	{
		
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



}
