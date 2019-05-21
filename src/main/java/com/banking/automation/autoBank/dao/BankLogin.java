package com.banking.automation.autoBank.dao;

import lombok.Data;

@Data
public class BankLogin {

    private String username;
    private String password;
    private String url;
    private String bankCode;

}
