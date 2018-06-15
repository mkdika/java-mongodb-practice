package com.mkdika.mongocrud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkdika.mongocrud.helper.JsonHelper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public class DocumentTest {
    
    public static void main(String[] args) throws IOException {
        Document doc = new Document()
                .append("str", "MongoDB, Hello")
                .append("int", 42)
                .append("l", 1L)
                .append("double", 1.1)
                .append("b", false)
                .append("date", new Date())
                .append("objectId", new ObjectId())
                .append("null", null)
                .append("embeddedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1, 2, 3, 4));
        System.out.println(JsonHelper.beautify(doc.toJson()));
    }    
}
