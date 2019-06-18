package com.irumole.ng.service;

import com.irumole.ng.dao.BankLogin;
import com.irumole.ng.dto.Account;
import com.irumole.ng.dto.Balance;
import com.irumole.ng.dto.Transaction;

import java.net.MalformedURLException;
import java.util.List;

public interface BankOperation {

    Account getAccounts(BankLogin bankLogin);

    Balance getBalance(BankLogin bankLogin);

    List<Transaction> getTransactions(BankLogin bankLogin);

}
