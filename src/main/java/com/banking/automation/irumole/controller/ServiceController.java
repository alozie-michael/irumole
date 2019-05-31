package com.banking.automation.irumole.controller;

import com.banking.automation.irumole.dto.GenericServiceResponse;
import com.banking.automation.irumole.dto.GenericServiceResponseBuilder;
import com.banking.automation.irumole.dto.Status;
import com.banking.automation.irumole.service.ServiceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

	@GetMapping(path = "/balance/{bankCode}")
	ResponseEntity<GenericServiceResponse> retrieveBalance(@PathVariable String bankCode) {
		User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder()
				.withStatus(Status.SUCCESS)
				.withData(serviceResolver.resolve(principle.getUsername(), bankCode, GET_BALANCE))
				.build());
	}

	@GetMapping(path = "/transactions/{bankCode}/from={from}&to={to}")
	ResponseEntity<GenericServiceResponse> retrieveTransactions(@PathVariable String from,
																@PathVariable String to,
																@PathVariable String bankCode) {
		//bankLogin.setFrom(from.replace("-", "/"));
		//bankLogin.setTo(to.replace("-", "/"));
		User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder()
				.withStatus(Status.SUCCESS)
				.withData(serviceResolver.resolve(principle.getUsername(), bankCode, GET_TRANSACTIONS))
				.build());
	}

	@GetMapping(path = "/account/{bankCode}")
	ResponseEntity<GenericServiceResponse> retrieveAccounts(@PathVariable String bankCode) {
		User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder()
				.withStatus(Status.SUCCESS)
				.withData(serviceResolver.resolve(principle.getUsername(), bankCode, GET_ACCOUNT))
				.build());
	}
}
