package com.irumole.ng.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExistingCustomerException extends RuntimeException {

    public ExistingCustomerException(String exceptionMessage){
        super(exceptionMessage);
    }
}
