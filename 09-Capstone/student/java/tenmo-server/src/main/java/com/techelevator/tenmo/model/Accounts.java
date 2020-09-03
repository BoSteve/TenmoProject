package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Accounts {

	private Long accountId;
	private Long userId;
	private BigDecimal balance;
	
	public Accounts() {
		
	}

	public Accounts(Long accountId, Long userId, BigDecimal balance) {
		this.accountId = accountId;
		this.userId = userId;
		this.balance = balance;
	}

	public void transferIn(Long accountId, BigDecimal amount) {
		this.accountId = accountId;
		this.balance = this.balance.add(amount);
	}
	
	public void transferOut(Long accountId, BigDecimal amount) {
		this.accountId = accountId;
		this.balance = this.balance.subtract(amount);
	}
	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Accounts [accountId=" + accountId + ", userId=" + userId + ", balance=" + balance + "]";
	}
	
}
