package com.irumole.ng.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankNotFoundExecption extends RuntimeException{

    public BankNotFoundExecption(String exceptionMessage){
        super(exceptionMessage);
    }
}
