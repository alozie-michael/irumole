package com.banking.automation.irumole.controller;

import com.banking.automation.irumole.dao.User;
import com.banking.automation.irumole.dto.Response;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/autoBank/user")
public class UserController {


	@PostMapping(path = "/signUp")
	Response signUp(@Valid @RequestBody User user, Errors errors) {

		return new Response();

	}

	@PostMapping(path = "/addAccount")
	Response addAccount(@Valid @RequestBody User user, Errors errors) {

		return new Response();

	}

	@PostMapping(path = "/getAccounts")
	Response deleteAccount(@Valid @RequestBody User user, Errors errors) {

		return new Response();

	}
}
