package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;

public interface BankOperation {

    String getAccounts(BankLogin bankLogin);

    String getBalance(BankLogin bankLogin);

    String getTransactions(BankLogin bankLogin);

}
