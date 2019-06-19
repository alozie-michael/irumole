package com.irumole.ng.repository;

import com.irumole.ng.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

@Repository
public class UserRepository extends Database {

    private MongoDatabase database = connect();

    public UserRepository(Environment environment) {
        super(environment);
    }

    private MongoCollection<User> getUserMongoCollection() {
        return database.getCollection("user", User.class);
    }

    public Optional<User> getUser(String username) {
        return Optional.ofNullable(getUserMongoCollection().find(eq("email", username)).first());
    }

    public void updateUser(String username, String target, Object value) {
        getUserMongoCollection().updateOne(eq("email", username), set(target, value));
    }

    public void insertUser(User user) {
        getUserMongoCollection().insertOne(user);
    }

    public void deleteUser(User user){
        getUserMongoCollection().deleteOne(eq("email", user.getEmail()));
    }
}
