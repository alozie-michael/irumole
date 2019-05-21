package com.banking.automation.irumole.dao;

import lombok.Data;

@Data
public class BankLogin {

    private String username;
    private String password;
    private String url;
    private String bankCode;

}
