/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkdika.mongocrud;

import com.mkdika.mongocrud.helper.JsonHelper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public class TestQuery2 {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("video");
        MongoCollection<Document> coll = db.getCollection("movieDetails");

        

        List<Document> all = coll.find()
                .into(new ArrayList<>());
        
        long c = all.stream().filter(TestQuery2::filCountry)
                .count();
       
        System.out.println(c);
        
        

//        JsonHelper.print(all.get(0).toJson());
//        all.stream().map(Document::toJson).forEach(JsonHelper::print);
//        System.out.println("...count: " + all.size());

    }
    
    static Boolean filCountry(Document doc) {
        
        List<String> countries = (List<String>) doc.get("countries");
        
        if (countries.size() > 1 && countries.get(1).equals("Sweden")) {
           return true;
        }
        return false;
        
        
    }

}
