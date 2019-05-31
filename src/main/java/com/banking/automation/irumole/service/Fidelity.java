package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;
import com.banking.automation.irumole.dto.Account;
import com.banking.automation.irumole.dto.Balance;
import com.banking.automation.irumole.dto.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class Fidelity implements BankOperation {

    @Override
    public Balance getBalance(BankLogin bankLogin){
        return new Balance();
    }

    @Override
    public List<Transaction> getTransactions(BankLogin bankLogin){
        return new ArrayList<>();
    }

    @Override
    public Account getAccounts(BankLogin bankLogin){
        return new Account();
    }

}
