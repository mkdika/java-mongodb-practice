package com.mkdika.mongocrud;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public class App {

    public static void main(String[] args) {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017), options);
        
        MongoDatabase db = client.getDatabase("m101");
        MongoCollection collection = db.getCollection("hw1");
      
        
        
        
    }

}
