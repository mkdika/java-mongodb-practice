/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkdika.mongocrud;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public class FinalTest7 {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("photo-sharing");
        MongoCollection<Document> images = db.getCollection("images");
        MongoCollection<Document> albums = db.getCollection("albums");

        List<Document> imagesList = images.find().into(new ArrayList<>());
        List<Document> albumsList = albums.find().into(new ArrayList<>());
        
        System.out.println(imagesList.size());
        System.out.println(albumsList.size());
        
//        for (Document d : imagesList) {
//            Integer id = d.getInteger("_id");
//            List<Document> xx = new ArrayList<>();
//            for (Document dx : albumsList) {
//                List<Document> ximg = (List<Document>) dx.get("images");
//                if (ximg.contains(id)) {
//                    xx.add(dx);
//                }
//            }
//            if (xx.size() < 1) {
//                images.deleteOne(new BasicDBObject("_id", d.getInteger("_id")));
//            }
//        }
//         System.out.println("after: "+imagesList.size());
        

    }

}
