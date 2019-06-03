package com.irumole.service.provider;

import com.irumole.dao.BankLogin;
import com.irumole.dto.Account;
import com.irumole.dto.Balance;
import com.irumole.dto.Transaction;
import com.irumole.service.BankOperation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandardChartered implements BankOperation {

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
