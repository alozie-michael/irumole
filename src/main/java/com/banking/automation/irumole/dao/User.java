package com.banking.automation.irumole.dao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class User {
	@NotNull
	@NotEmpty
	private String lastName;
	@NotNull
	@NotEmpty
	private String otherName;
	@NotNull
	@NotEmpty
	private String bvn;
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String phoneNumber;
	@NotNull
	@NotEmpty
	private String password;
}
