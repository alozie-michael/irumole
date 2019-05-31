package com.banking.automation.irumole.repository;

import com.banking.automation.irumole.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

@Repository
public class UserRepository extends Database {

    private static MongoDatabase database = connect();

    public MongoCollection<User> getUserMongoCollection(){
        return database.getCollection("user", com.banking.automation.irumole.model.User.class);
    }

    public Optional<com.banking.automation.irumole.model.User> getUser(String username){
        return Optional.ofNullable(getUserMongoCollection().find(eq("email", username)).first());
    }

    public void updateUser(String username, String target, Object value){
        getUserMongoCollection().updateOne(eq("email", username), set(target, value));
    }

    public void insertUser(User user){
        getUserMongoCollection().insertOne(user);
    }
}
