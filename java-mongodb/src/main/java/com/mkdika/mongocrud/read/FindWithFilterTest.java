package com.mkdika.mongocrud.read;

import com.mkdika.mongocrud.helper.JsonHelper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public class FindWithFilterTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("findWithFilterTest");

        coll.drop();

        for (int i = 0; i < 10; i++) {
            coll.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100)));
        }
        
        // first way to filter within mongoDb query
        // Bson filter = new Document("x",0).append("y", new Document("$gt",10));

        // anothter way to filter within mongoDb query
        Bson filter = and(eq("x",0),gt("y",10),lt("y",99));
        
        List<Document> all = coll.find(filter).into(new ArrayList<>());
        
        all.stream().map(Document::toJson).forEach(JsonHelper::print);
        
        System.out.println("...count: " + all.size());
        

    }

}
