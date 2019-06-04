package com.irumole.ng.repository;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

        List<ServerAddress> servers = new ArrayList<>();
        servers.add(new ServerAddress("irumole-shard-00-00-t43pc.mongodb.net", 27017));
        servers.add(new ServerAddress("irumole-shard-00-02-t43pc.mongodb.net", 27017));

        MongoCredential credential = MongoCredential.createCredential(environment.getProperty("mongo.username"), environment.getProperty("mongo.database"), environment.getProperty("mongo.password").toCharArray());

        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .credential(credential)
                .retryWrites(true)
                .applyToConnectionPoolSettings(builder ->
                builder.maxConnectionIdleTime(6000, TimeUnit.MILLISECONDS))
                .applyToSslSettings(builder -> builder.enabled(true))
                .applyToClusterSettings(builder -> {
                    builder.hosts(servers);
                    builder.requiredReplicaSetName("irumole-shard-0");
                })
                .build();

        MongoClient mongoClient = MongoClients.create(settings);

        return mongoClient.getDatabase(environment.getProperty("mongo.database"));
    }
}
