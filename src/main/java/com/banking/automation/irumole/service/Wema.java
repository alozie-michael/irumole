package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;

public class Wema {

    public String getBalance(BankLogin bankLogin){
        return "Your Wema Bank Balances";
    }

    public String getTransactions(BankLogin bankLogin){
        return "Your Wema Bank account transactions";
    }

    public String getAccounts(BankLogin bankLogin){
        return "Your Wema Bank accounts";
    }

}
