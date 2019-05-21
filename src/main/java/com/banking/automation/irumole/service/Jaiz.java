package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;
import org.springframework.stereotype.Service;

@Service
public class Jaiz {

    public String getBalance(BankLogin bankLogin){
        return "Your Jaiz Bank Balances";
    }

    public String getTransactions(BankLogin bankLogin){
        return "Your Jaiz Bank account transactions";
    }

    public String getAccounts(BankLogin bankLogin){
        return "Your Jaiz Bank accounts";
    }

}
