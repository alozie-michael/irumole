package com.irumole.dao;

import lombok.Data;

@Data
public class BankLogin {
    private String username;
    private String password;
    private String url;
    private String bankCode;
    private String from;
    private String to;

    public BankLogin(){

    }

    public BankLogin(String bankCode){
        this.bankCode = bankCode;
    }

    public BankLogin(String bankCode, String from, String to){
        this.bankCode = bankCode;
        this.from = from;
        this.to = to;
    }
}
