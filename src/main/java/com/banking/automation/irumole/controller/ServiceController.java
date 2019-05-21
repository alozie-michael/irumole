package com.banking.automation.irumole.controller;

import javax.validation.Valid;

import com.banking.automation.irumole.dao.BankLogin;
import com.banking.automation.irumole.service.ServiceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.banking.automation.irumole.dao.Service.*;

@RestController
@RequestMapping(path = "/autoBank")
public class ServiceController {

	private final ServiceResolver serviceResolver;

	@Autowired
	public ServiceController(ServiceResolver serviceResolver) {
		this.serviceResolver = serviceResolver;
	}

	@PostMapping(path = "/getBalance/")
	String getBalance(@Valid @RequestBody BankLogin bankLogin, Errors errors) {
		return serviceResolver.resolve(bankLogin, GET_BALANCE);
	}

	@PostMapping(path = "/getTransactions")
	String getTransactions(@Valid @RequestBody BankLogin bankLogin, Errors errors) {
		return serviceResolver.resolve(bankLogin, GET_TRANSACTIONS);
	}

	@PostMapping(path = "/getAccounts")
	String getAccounts(@Valid @RequestBody BankLogin bankLogin, Errors errors) {
		return serviceResolver.resolve(bankLogin, GET_ACOOUNT);
	}
}
