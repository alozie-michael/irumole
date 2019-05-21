package com.banking.automation.autoBank.service;

import com.banking.automation.autoBank.dao.BankLogin;
import org.springframework.stereotype.Service;

@Service
public class Zenith {

    public String getBalance(BankLogin bankLogin){
        return "Your Zenith Bank Balances";
    }

    public String getTransactions(BankLogin bankLogin){
        return "Your Zenith Bank account transactions";
    }

    public String getAccounts(BankLogin bankLogin){
        return "Your Zenith Bank accounts";
    }

}
