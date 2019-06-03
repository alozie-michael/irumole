package com.irumole.service;

import com.irumole.dao.BankLogin;
import com.irumole.dto.Account;
import com.irumole.dto.Balance;
import com.irumole.dto.Transaction;

import java.util.List;

public interface BankOperation {

    Account getAccounts(BankLogin bankLogin);

    Balance getBalance(BankLogin bankLogin);

    List<Transaction> getTransactions(BankLogin bankLogin);

}
