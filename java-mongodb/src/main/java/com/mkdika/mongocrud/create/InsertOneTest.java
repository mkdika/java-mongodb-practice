
package com.mkdika.mongocrud.create;

import com.mkdika.mongocrud.helper.JsonHelper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import org.bson.Document;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public class InsertOneTest {
    
    public static void main(String[] args) throws IOException {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("insertTable");
        
        coll.drop();
        
        Document person = new Document("name","Maikel Chandika")
                                .append("age", 30)
                                .append("profession", "Software Engineer");
        
        System.out.println("Before insert:"); // there is not _id field
        JsonHelper.print(person.toJson());
        
        System.out.println();
        coll.insertOne(person);
        
        System.out.println("After insert:"); // there is _id field
        JsonHelper.print(person.toJson());
        
      
    }
    
}
