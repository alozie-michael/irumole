package com.irumole.dto;

import lombok.Data;

@Data
public class Account {
	private String accountName;
	private String accountNumber;
	private String accountType;

	public Account(){

	}

	public Account(String accountName, String accountNumber, String accountType) {
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
	}
}
