package com.banking.automation.autoBank.service;

import com.banking.automation.autoBank.dao.BankLogin;
import org.springframework.stereotype.Service;

@Service
public class Fidelity {

    public String getBalance(BankLogin bankLogin){
        return "Your Fidelity Bank Balances";
    }

    public String getTransactions(BankLogin bankLogin){
        return "Your Fidelity Bank account transactions";
    }

    public String getAccounts(BankLogin bankLogin){
        return "Your Fidelity Bank accounts";
    }

}
