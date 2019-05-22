package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;

class BankService {

    private BankOperation bankOperation;

    BankService(BankOperation bankOperation){
        this.bankOperation = bankOperation;
    }

    String returnBalance(BankLogin bankLogin){
        return bankOperation.getBalance(bankLogin);
    }

    String returnTransactions(BankLogin bankLogin){
        return bankOperation.getTransactions(bankLogin);
    }

    String returnAccounts(BankLogin bankLogin){
        return bankOperation.getBalance(bankLogin);
    }
}
