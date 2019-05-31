package com.banking.automation.irumole.dto;

import lombok.Data;

@Data
public final class GenericServiceResponseBuilder<T> {

    private Status status;
    private String statusMessage;
    private T data;

    public static GenericServiceResponseBuilder aGenericServiceResponseBuilder(){
        return new GenericServiceResponseBuilder();
    }

    public GenericServiceResponseBuilder withStatus(Status status){
        this.status = status;
        return this;
    }

    public GenericServiceResponseBuilder withStatusMessage(String statusMessage){
        this.statusMessage = statusMessage;
        return this;
    }

    public GenericServiceResponseBuilder withData(T data){
        this.data = data;
        return this;
    }

    public GenericServiceResponse<T> build(){
        GenericServiceResponse<T> genericServiceResponse = new GenericServiceResponse<>();
        genericServiceResponse.setData(data);
        genericServiceResponse.setStatusMessage(statusMessage);
        genericServiceResponse.setStatus(status);
        return genericServiceResponse;
    }

}
