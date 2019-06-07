package com.irumole.ng.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Component
public class Database {

    private final
    Environment environment;

    @Autowired
    public Database(Environment environment) {
        this.environment = environment;
    }

    MongoDatabase connect() {

        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = MongoClients.create(environment.getProperty("mongo.uri"));

        return mongoClient.getDatabase(environment.getProperty("mongo.database")).withCodecRegistry(pojoCodecRegistry);
    }
}
