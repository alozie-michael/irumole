package com.banking.automation.autoBank.service;

import com.banking.automation.autoBank.dao.BankLogin;
import org.springframework.stereotype.Service;

@Service
public class Citi {

    public String getBalance(BankLogin bankLogin){
        return "Your Citi Bank Balances";
    }

    public String getTransactions(BankLogin bankLogin){
        return "Your Citi Bank account transactions";
    }

    public String getAccounts(BankLogin bankLogin){
        return "Your Citi Bank accounts";
    }

}
