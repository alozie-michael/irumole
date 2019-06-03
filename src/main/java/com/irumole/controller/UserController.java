package com.irumole.controller;

import com.irumole.dao.User;
import com.irumole.dao.UserBank;
import com.irumole.dto.GenericServiceResponse;
import com.irumole.dto.GenericServiceResponseBuilder;
import com.irumole.dto.Status;
import com.irumole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<GenericServiceResponse> signUp(@Valid @RequestBody User user, Errors errors) {
		if(errors.hasErrors()){
			return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder().withStatus(Status.FAILED).withStatusMessage("Field error").build());
		}
		return ResponseEntity.ok(getResponse(userService.signUp(user)));
	}

	@PostMapping(path = "/bank")
    public ResponseEntity<GenericServiceResponse> addBank(@Valid @RequestBody UserBank userBank, Errors errors) {
		if(errors.hasErrors()){
			return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder().withStatus(Status.FAILED).withStatusMessage("Field error").build());
		}
		org.springframework.security.core.userdetails.User principle = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(getResponse(userService.addBank(principle.getUsername(), userBank)));
	}

	@DeleteMapping(path = "/bank/{bankCode}")
    public ResponseEntity<GenericServiceResponse> deleteAccount(@PathVariable String bankCode) {
		org.springframework.security.core.userdetails.User principle = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(getResponse(userService.removeBank(principle.getUsername(), bankCode)));
	}

	private GenericServiceResponse getResponse(String status){
		if(status.equals("success"))
			return GenericServiceResponseBuilder.aGenericServiceResponseBuilder().withStatus(Status.SUCCESS).withStatusMessage(status).build();
		else
			return GenericServiceResponseBuilder.aGenericServiceResponseBuilder().withStatus(Status.FAILED).withStatusMessage(status).build();
	}
}
