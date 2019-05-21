package com.banking.automation.autoBank.service;

import com.banking.automation.autoBank.dao.BankLogin;
import org.springframework.stereotype.Service;

@Service
public class GuaranteedTrust {

    public String getBalance(BankLogin bankLogin){
        return "Your GT Bank Balances";
    }

    public String getTransactions(BankLogin bankLogin){
        return "Your GT Bank account transactions";
    }

    public String getAccounts(BankLogin bankLogin){
        return "Your GT Bank accounts";
    }

}
