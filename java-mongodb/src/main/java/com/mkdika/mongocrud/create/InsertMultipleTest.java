package com.mkdika.mongocrud.create;

import com.mkdika.mongocrud.helper.JsonHelper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bson.Document;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public class InsertMultipleTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("insertTable");

        coll.drop();

        List<Document> listDoc = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Document doc = new Document();
            doc.append("random", new Random().nextInt(100));
            listDoc.add(doc);
        }
        
        coll.insertMany(listDoc);
        
        System.out.println("After insertion: ");
        listDoc.forEach(JsonHelper::print);
    }
}
