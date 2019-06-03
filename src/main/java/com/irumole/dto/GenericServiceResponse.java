package com.irumole.dto;

import lombok.Data;

@Data
public class GenericServiceResponse<T> {

    private Status status;
    private String statusMessage;
    private T data;

}
