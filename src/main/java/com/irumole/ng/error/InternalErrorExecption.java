package com.irumole.ng.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorExecption extends RuntimeException{

    public InternalErrorExecption(String exceptionMessage){
        super(exceptionMessage);
    }
}
