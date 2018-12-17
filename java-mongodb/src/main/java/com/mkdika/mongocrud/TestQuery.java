package com.mkdika.mongocrud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public class TestQuery {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> coll = db.getCollection("grades");

        Bson filter = eq("type", "homework");

        List<Document> all = coll.find(filter)
                .sort(new Document("student_id", 1).append("score", 1))
                .into(new ArrayList<>());

//        JsonHelper.print(all.get(0).toJson());
//        all.stream().map(Document::toJson).forEach(JsonHelper::print);
        Integer id = -1;
        int c = 0;
        for (Document cur : all) {
            if (!Objects.equals(cur.getInteger("student_id"), id)) {
                System.out.println("id= "+id+", cur_id= " + cur.getInteger("student_id")+", remove!");
                id = cur.getInteger("student_id");
                coll.deleteOne(cur);
                c++;
            } 
        }
        System.out.println("> deleted: " + c);

        all = coll.find()
                .sort(new Document("student_id", 1).append("score", -1))
                .into(new ArrayList<>());

        System.out.println("...count:" + all.size());
    }

}
