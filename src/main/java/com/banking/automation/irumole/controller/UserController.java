package com.banking.automation.irumole.controller;

import com.banking.automation.irumole.dao.User;
import com.banking.automation.irumole.dao.UserBank;
import com.banking.automation.irumole.dto.GenericServiceResponse;
import com.banking.automation.irumole.dto.GenericServiceResponseBuilder;
import com.banking.automation.irumole.dto.Status;
import com.banking.automation.irumole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	private final
	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(path = "/signUp")
	GenericServiceResponse signUp(@Valid @RequestBody User user, Errors errors) {
		if(errors.hasErrors()){
			return GenericServiceResponseBuilder.aGenericServiceResponseBuilder().withStatus(Status.FAILED).withStatusMessage("Field error").build();
		}
		return getResponse(userService.signUp(user));
	}

	@PostMapping(path = "/addBank")
    GenericServiceResponse addBank(@Valid @RequestBody UserBank userBank, Errors errors) {
		org.springframework.security.core.userdetails.User principle = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return getResponse(userService.addBank(principle.getUsername(), userBank));
	}

	@DeleteMapping(path = "/removeBank/{bankCode}")
    GenericServiceResponse deleteAccount(@PathVariable String bankCode) {
		org.springframework.security.core.userdetails.User principle = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return getResponse(userService.removeBank(principle.getUsername(), bankCode));
	}

	private GenericServiceResponse getResponse(String status){
		if(status.equals("success"))
			return GenericServiceResponseBuilder.aGenericServiceResponseBuilder().withStatus(Status.SUCCESS).withStatusMessage(status).build();
		else
			return GenericServiceResponseBuilder.aGenericServiceResponseBuilder().withStatus(Status.FAILED).withStatusMessage(status).build();
	}
}
