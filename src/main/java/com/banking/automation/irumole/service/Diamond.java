package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;
import org.springframework.stereotype.Service;

@Service
public class Diamond implements BankOperation {

    @Override
    public String getBalance(BankLogin bankLogin){
        return "Your Diamond Bank Balances";
    }

    @Override
    public String getTransactions(BankLogin bankLogin){
        return "Your Diamond Bank account transactions";
    }

    @Override
    public String getAccounts(BankLogin bankLogin){
        return "Your Diamond Bank accounts";
    }

}
