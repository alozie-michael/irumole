package com.banking.automation.autoBank.service;

import com.banking.automation.autoBank.dao.BankLogin;
import org.springframework.stereotype.Service;

@Service
public class Sterling {

    public String getBalance(BankLogin bankLogin){
        return "Your Sterling Bank Balances";
    }

    public String getTransactions(BankLogin bankLogin){
        return "Your Sterling Bank account transactions";
    }

    public String getAccounts(BankLogin bankLogin){
        return "Your Sterling Bank accounts";
    }

}
