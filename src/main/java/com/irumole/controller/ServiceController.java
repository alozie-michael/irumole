package com.irumole.controller;

import com.irumole.dao.BankLogin;
import com.irumole.dao.Service;
import com.irumole.dto.GenericServiceResponse;
import com.irumole.dto.GenericServiceResponseBuilder;
import com.irumole.dto.Status;
import com.irumole.service.ServiceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/service")
public class ServiceController {

	private final ServiceResolver serviceResolver;

	@Autowired
	public ServiceController(ServiceResolver serviceResolver) {
		this.serviceResolver = serviceResolver;
	}

	@GetMapping(path = "/balance/{bankCode}")
	public ResponseEntity<GenericServiceResponse> retrieveBalance(@PathVariable String bankCode) {
		User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder()
				.withStatus(Status.SUCCESS)
				.withData(serviceResolver.resolve(principle.getUsername(), new BankLogin(bankCode), Service.GET_BALANCE))
				.build());
	}

	@GetMapping(path = "/transactions/{bankCode}/from={from}&to={to}")
	public ResponseEntity<GenericServiceResponse> retrieveTransactions(@PathVariable String from,
																@PathVariable String to,
																@PathVariable String bankCode) {
		User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder()
				.withStatus(Status.SUCCESS)
				.withData(serviceResolver.resolve(principle.getUsername(), new BankLogin(bankCode, from, to), Service.GET_TRANSACTIONS))
				.build());
	}

	@GetMapping(path = "/account/{bankCode}")
	public ResponseEntity<GenericServiceResponse> retrieveAccounts(@PathVariable String bankCode) {
		User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder()
				.withStatus(Status.SUCCESS)
				.withData(serviceResolver.resolve(principle.getUsername(), new BankLogin(bankCode), Service.GET_ACCOUNT))
				.build());
	}
}
