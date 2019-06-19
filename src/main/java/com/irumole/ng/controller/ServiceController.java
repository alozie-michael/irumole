package com.irumole.ng.controller;

import com.irumole.ng.dao.BankLogin;
import com.irumole.ng.dao.Service;
import com.irumole.ng.dao.TransactionDate;
import com.irumole.ng.dto.GenericServiceResponse;
import com.irumole.ng.dto.GenericServiceResponseBuilder;
import com.irumole.ng.dto.Status;
import com.irumole.ng.service.ServiceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder()
                .withStatus(Status.SUCCESS)
                .withData(serviceResolver.resolve(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), new BankLogin(bankCode), Service.GET_BALANCE))
                .build());
    }

    @GetMapping(path = "/transactions/{bankCode}/from/{fromDay}-{fromMonth}-{fromYear}/to/{toDay}-{toMonth}-{toYear}")
    public ResponseEntity<GenericServiceResponse> retrieveTransactions(@PathVariable String fromDay,
                                                                       @PathVariable String fromMonth,
                                                                       @PathVariable String fromYear,
                                                                       @PathVariable String toDay,
                                                                       @PathVariable String toMonth,
                                                                       @PathVariable String toYear,
                                                                       @PathVariable String bankCode) {
        TransactionDate from = new TransactionDate(fromDay, fromMonth, fromYear);
        TransactionDate to = new TransactionDate(toDay, toMonth, toYear);
        return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder()
                .withStatus(Status.SUCCESS)
                .withData(serviceResolver.resolve(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), new BankLogin(bankCode, from, to), Service.GET_TRANSACTIONS))
                .build());
    }

    @GetMapping(path = "/account/{bankCode}")
    public ResponseEntity<GenericServiceResponse> retrieveAccounts(@PathVariable String bankCode) {
        return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponseBuilder()
                .withStatus(Status.SUCCESS)
                .withData(serviceResolver.resolve(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), new BankLogin(bankCode), Service.GET_ACCOUNT))
                .build());
    }
}
