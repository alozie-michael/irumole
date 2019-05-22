package com.banking.automation.irumole.controller;

import javax.validation.Valid;

import com.banking.automation.irumole.dao.BankLogin;
import com.banking.automation.irumole.service.ServiceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import static com.banking.automation.irumole.dao.Service.*;

@RestController
@RequestMapping(path = "/service")
public class ServiceController {

	private final ServiceResolver serviceResolver;

	@Autowired
	public ServiceController(ServiceResolver serviceResolver) {
		this.serviceResolver = serviceResolver;
	}

	@PostMapping(path = "/balance")
	String retrieveBalance(@Valid @RequestBody BankLogin bankLogin, Errors errors) {
		return serviceResolver.resolve(bankLogin, GET_BALANCE);
	}

	@PostMapping(path = "/transactions")
	String retrieveTransactions(@Valid @RequestBody BankLogin bankLogin, Errors errors) {
		return serviceResolver.resolve(bankLogin, GET_TRANSACTIONS);
	}

	@PostMapping(path = "/accounts")
	String retrieveAccounts(@Valid @RequestBody BankLogin bankLogin, Errors errors) {
		return serviceResolver.resolve(bankLogin, GET_ACOOUNT);
	}
}
