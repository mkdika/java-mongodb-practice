/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkdika.mongocrud.test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public class TestInsert {

    public static void main(String[] args) {
        MongoClient c = new MongoClient();
        MongoDatabase db = c.getDatabase("test");
        MongoCollection<Document> animals = db.getCollection("animals");

        Document animal = new Document("animal", "monkey");

        animals.insertOne(animal);
        animal.remove("animal");
        animal.append("animal", "cat");
        animals.insertOne(animal);
//        animal.remove("animal");
//        animal.append("animal", "lion");
//        animals.insertOne(animal);
        
        System.out.println(animals.count());
    }

}