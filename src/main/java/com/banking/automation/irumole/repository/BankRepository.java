package com.banking.automation.irumole.repository;

import com.banking.automation.irumole.model.Bank;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class BankRepository extends Database {

    private static MongoDatabase database = connect();

    private MongoCollection<Bank> getBankMongoCollection(){
        return database.getCollection("bank", Bank.class);
    }

    public Optional<Bank> getBank(String bankCode){
        return Optional.ofNullable(getBankMongoCollection().find(eq("bankCode", bankCode)).first());
    }
}
