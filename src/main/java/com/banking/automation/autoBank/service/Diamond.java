package com.banking.automation.autoBank.service;

import com.banking.automation.autoBank.dao.BankLogin;
import org.springframework.stereotype.Service;

@Service
public class Diamond {

    public String getBalance(BankLogin bankLogin){
        return "Your Diamond Bank Balances";
    }

    public String getTransactions(BankLogin bankLogin){
        return "Your Diamond Bank account transactions";
    }

    public String getAccounts(BankLogin bankLogin){
        return "Your Diamond Bank accounts";
    }

}
