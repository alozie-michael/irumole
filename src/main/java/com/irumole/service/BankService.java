package com.irumole.service;

import com.irumole.dao.BankLogin;
import com.irumole.dto.Account;
import com.irumole.dto.Balance;
import com.irumole.dto.Transaction;

import java.util.List;

class BankService {

    private BankOperation bankOperation;

    BankService(BankOperation bankOperation){
        this.bankOperation = bankOperation;
    }

    Balance returnBalance(BankLogin bankLogin){
        return bankOperation.getBalance(bankLogin);
    }

    List<Transaction> returnTransactions(BankLogin bankLogin){
        return bankOperation.getTransactions(bankLogin);
    }

    Account returnAccounts(BankLogin bankLogin){
        return bankOperation.getAccounts(bankLogin);
    }
}
