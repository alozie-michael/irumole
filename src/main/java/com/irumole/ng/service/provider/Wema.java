package com.irumole.ng.service.provider;

import com.irumole.ng.dao.BankLogin;
import com.irumole.ng.dto.Account;
import com.irumole.ng.dto.Balance;
import com.irumole.ng.dto.Transaction;
import com.irumole.ng.service.BankOperation;

import java.util.ArrayList;
import java.util.List;

public class Wema implements BankOperation {

    @Override
    public Balance getBalance(BankLogin bankLogin) {
        return new Balance();
    }

    @Override
    public List<Transaction> getTransactions(BankLogin bankLogin) {
        return new ArrayList<>();
    }

    @Override
    public Account getAccounts(BankLogin bankLogin) {
        return new Account();
    }

}
