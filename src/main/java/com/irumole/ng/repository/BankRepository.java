package com.irumole.ng.repository;

import com.irumole.ng.model.Bank;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;


@Repository
public class BankRepository extends Database {

    private MongoDatabase database = connect();

    public BankRepository(Environment environment) {
        super(environment);
    }

    private MongoCollection<Bank> getBankMongoCollection() {
        return database.getCollection("bank", Bank.class);
    }

    public Optional<Bank> getBank(String bankCode) {
        return Optional.ofNullable(getBankMongoCollection().find(eq("bankCode", bankCode)).first());
    }
}
