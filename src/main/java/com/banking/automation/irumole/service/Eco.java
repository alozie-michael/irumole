package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;
import org.springframework.stereotype.Service;

@Service
public class Eco implements BankOperation {

    @Override
    public String getBalance(BankLogin bankLogin){
        return "Your Eco Bank Balances";
    }

    @Override
    public String getTransactions(BankLogin bankLogin){
        return "Your Eco Bank account transactions";
    }

    @Override
    public String getAccounts(BankLogin bankLogin){
        return "Your Eco Bank accounts";
    }

}
