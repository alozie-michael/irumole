package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.BankLogin;
import com.banking.automation.irumole.dto.Account;
import com.banking.automation.irumole.dto.Balance;
import com.banking.automation.irumole.dto.Transaction;

import java.util.List;

public interface BankOperation {

    Account getAccounts(BankLogin bankLogin);

    Balance getBalance(BankLogin bankLogin);

    List<Transaction> getTransactions(BankLogin bankLogin);

}
