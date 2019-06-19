package com.irumole.ng.dao;

import lombok.Data;

@Data
public class BankLogin {
    private String username;
    private String password;
    private String url;
    private String bankCode;
    private TransactionDate from;
    private TransactionDate to;

    public BankLogin() {

    }

    public BankLogin(String bankCode) {
        this.bankCode = bankCode;
    }

    public BankLogin(String bankCode, TransactionDate from, TransactionDate to) {
        this.bankCode = bankCode;
        this.from = from;
        this.to = to;
    }
}
