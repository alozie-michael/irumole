package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;
import org.springframework.stereotype.Service;

@Service
public class Stanbic {

    public String getBalance(BankLogin bankLogin){
        return "Your Stanbic Bank Balances";
    }

    public String getTransactions(BankLogin bankLogin){
        return "Your Stanbic Bank account transactions";
    }

    public String getAccounts(BankLogin bankLogin){
        return "Your Stanbic Bank accounts";
    }

}
