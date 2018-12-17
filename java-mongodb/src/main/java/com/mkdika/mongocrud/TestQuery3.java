package com.mkdika.mongocrud;

import com.mkdika.mongocrud.helper.JsonHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import static java.util.Locale.filter;
import java.util.stream.Stream;
import org.bson.Document;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public class TestQuery3 {

    static Boolean filterHomework(Document d) {
        if (d.getString("type").equals("homework")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("school");
        MongoCollection<Document> coll = db.getCollection("students");

        List<Document> all = coll.find()
                .into(new ArrayList<>());

        for (Document d : all) {

            List<Document> scores = d.get("scores", new ArrayList<>());

            Double scorex
                    = scores.stream().filter(TestQuery3::filterHomework).sorted((d1, d2) -> {
                        Double do1 = d1.getDouble("score");
                        Double do2 = d2.getDouble("score");
                        return do1.compareTo(do2);
                    }).limit(1).mapToDouble(dxx -> dxx.getDouble("score")).sum();
            System.out.println(scorex);

            BasicDBObject query = new BasicDBObject("_id", d.getInteger("_id"));
            BasicDBObject fields = new BasicDBObject("scores",
                    new BasicDBObject("score", scorex));
            BasicDBObject update = new BasicDBObject("$pull", fields);

            coll.updateMany(query, update);

        }

//        BasicDBObject query = new BasicDBObject("_id", doc.getInteger("_id"));
//        BasicDBObject fields = new BasicDBObject("scores",
//                new BasicDBObject("score", 20.18160621941858d));
//        BasicDBObject update = new BasicDBObject("$pull",fields);
//        
//        coll.updateMany(query, update);
//        
//        all = coll.find(filter)
//                .into(new ArrayList<>());
//
//        System.out.println(all.size());
//
//       doc = all.get(0);
//
//        JsonHelper.print(doc);

        /*
        BasicDBObject query = new BasicDBObject("_id", 73);
    BasicDBObject fields = new BasicDBObject("goals", 
        new BasicDBObject( "goal", 4));
    BasicDBObject update = new BasicDBObject("$pull",fields);

    games.update( query, update );
         */
    }
}
