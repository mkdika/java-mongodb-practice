package com.mkdika.mongocrud;

import com.mkdika.mongocrud.helper.JsonHelper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public class FindTest {

    public static void main(String[] args) throws IOException {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("findTest");

        coll.drop();

        for (int i = 0; i < 10; i++) {
            coll.insertOne(new Document("x", i));
        }

        System.out.println("Find One: ");
        JsonHelper.print(coll.find().first().toJson());

        System.out.println("Find all with into:");
        List<Document> all = coll.find().into(new ArrayList<>());
        all.stream().map(Document::toJson).forEach(JsonHelper::print);

        System.out.println("Find all with iteration: ");
        try (MongoCursor<Document> cursor = coll.find().iterator()) {
            while (cursor.hasNext()) {
                Document cur = cursor.next();
                JsonHelper.print(cur.toJson());
            }
        }

        System.out.println("Count:");
        System.out.println("........Count: " + coll.count());

    }

}
