package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;
import org.springframework.stereotype.Service;

@Service
public class Skye implements BankOperation {

    @Override
    public String getBalance(BankLogin bankLogin){
        return "Your Skye Bank Balances";
    }

    @Override
    public String getTransactions(BankLogin bankLogin){
        return "Your Skye Bank account transactions";
    }

    @Override
    public String getAccounts(BankLogin bankLogin){
        return "Your Skye Bank accounts";
    }

}
