package com.irumole.ng.controller;

import com.irumole.ng.dao.User;
import com.irumole.ng.dao.UserBank;
import com.irumole.ng.dto.GenericServiceResponse;
import com.irumole.ng.dto.GenericServiceResponseBuilder;
import com.irumole.ng.dto.Status;
import com.irumole.ng.service.UserService;
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

    @PostMapping(path = "/register")
    public ResponseEntity<GenericServiceResponse> signUp(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder().withStatus(Status.FAILED).withStatusMessage("Field error").build());
        }
        return ResponseEntity.ok(getResponse(userService.signUp(user)));
    }

    @PostMapping(path = "/bank")
    public ResponseEntity<GenericServiceResponse> addBank(@Valid @RequestBody UserBank userBank, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder().withStatus(Status.FAILED).withStatusMessage("Field error").build());
        }
        return ResponseEntity.ok(getResponse(userService.addBank(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), userBank)));
    }

    @DeleteMapping(path = "/bank/{bankCode}")
    public ResponseEntity<GenericServiceResponse> deleteAccount(@PathVariable String bankCode) {
        return ResponseEntity.ok(getResponse(userService.removeBank(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), bankCode)));
    }

    private GenericServiceResponse getResponse(String status) {
        if (status.equals("success"))
            return GenericServiceResponseBuilder.aGenericServiceResponseBuilder().withStatus(Status.SUCCESS).withStatusMessage(status).build();
        else
            return GenericServiceResponseBuilder.aGenericServiceResponseBuilder().withStatus(Status.FAILED).withStatusMessage(status).build();
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<GenericServiceResponse> deleteUser() {
        return ResponseEntity.ok(getResponse(userService.deleteUser(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())));
    }
}
