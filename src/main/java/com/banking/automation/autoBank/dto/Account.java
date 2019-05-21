package com.banking.automation.autoBank.dto;

import lombok.Data;

@Data
public class Account {
	private String accountName;
	private String accountNumber;
	private double accountBalance;
	private String accountType;
	
	public Account(String accountName, String accountNumber, double accountBalance, String accountType) {
		super();
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
	}
}
