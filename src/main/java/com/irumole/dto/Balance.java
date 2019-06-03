package com.irumole.dto;

import lombok.Data;

@Data
public class Balance {
	private String accountName;
	private String accountNumber;
	private double accountBalance;
	private String accountType;

	public Balance(){

	}
	
	public Balance(String accountName, String accountNumber, double accountBalance, String accountType) {
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
	}
}
