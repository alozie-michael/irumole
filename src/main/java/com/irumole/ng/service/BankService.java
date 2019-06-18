package com.irumole.ng.service;

import com.irumole.ng.dao.BankLogin;
import com.irumole.ng.dto.Account;
import com.irumole.ng.dto.Balance;
import com.irumole.ng.dto.Transaction;

import java.net.MalformedURLException;
import java.util.List;

class BankService {

    private BankOperation bankOperation;

    BankService(BankOperation bankOperation) {
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
