package com.irumole.ng.dto;

import lombok.Data;

@Data
public class GenericServiceResponse<T> {

    private Status status;
    private String statusMessage;
    private T data;

    GenericServiceResponse(){

    }

    public GenericServiceResponse(Status status, String statusMessage, T data){
        this.status = status;
        this.statusMessage = statusMessage;
        this.data = data;
    }

}
