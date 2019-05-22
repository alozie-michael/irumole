package com.banking.automation.irumole.service;

import com.banking.automation.irumole.dao.User;
import com.banking.automation.irumole.model.Database;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String signUp(User user) {
        MongoDatabase database = Database.connect();
        MongoCollection<com.banking.automation.irumole.model.User> collection = database.getCollection("user", com.banking.automation.irumole.model.User.class);
        com.banking.automation.irumole.model.User newUser = new com.banking.automation.irumole.model.User();
        BeanUtils.copyProperties(user, newUser);
        collection.insertOne(newUser);
        return "Successful";
    }

    @Override
    public String addBank() {
        return null;
    }

    @Override
    public String removeBank() {
        return null;
    }
}
