package com.banking.automation.irumole.controller;

import com.banking.automation.irumole.dao.User;
import com.banking.automation.irumole.dto.Response;
import com.banking.automation.irumole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	String signUp(@Valid @RequestBody User user, Errors errors) {
		return userService.signUp(user);
	}

	@PostMapping(path = "/addBank")
	Response addAccount(@Valid @RequestBody User user, Errors errors) {
		return new Response();
	}

	@PostMapping(path = "/removeBank")
	Response deleteAccount(@Valid @RequestBody User user, Errors errors) {
		return new Response();
	}
}
