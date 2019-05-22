package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;

public class Wema implements BankOperation {

    @Override
    public String getBalance(BankLogin bankLogin){
        return "Your Wema Bank Balances";
    }

    @Override
    public String getTransactions(BankLogin bankLogin){
        return "Your Wema Bank account transactions";
    }

    @Override
    public String getAccounts(BankLogin bankLogin){
        return "Your Wema Bank accounts";
    }

}
