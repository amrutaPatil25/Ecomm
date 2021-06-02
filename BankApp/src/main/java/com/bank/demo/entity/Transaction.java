package com.bank.demo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Transaction {
	@Id
	@GeneratedValue
	private int transactionId;
	private double amount;
	private Date transactionDate;
	private String transactionType;
	private Integer toAccNo;
	@ManyToOne
	@JoinColumn(name="accountId")
	private Account account;
	
	public Transaction() {
	
	}

	public Integer getToAccNo() {
		return toAccNo;
	}

	public void setToAccNo(Integer toAccNo) {
		this.toAccNo = toAccNo;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	


}
