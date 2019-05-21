package com.banking.automation.autoBank.service;

import com.banking.automation.autoBank.dao.BankLogin;
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
