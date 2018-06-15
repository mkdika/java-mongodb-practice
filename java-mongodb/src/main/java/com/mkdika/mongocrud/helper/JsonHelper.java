package com.mkdika.mongocrud.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public final class JsonHelper {

    private JsonHelper() {
    }

    public static String beautify(String json)  {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object obj = mapper.readValue(json, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException ex) {
            Logger.getLogger(JsonHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static void print(String json)  {
        System.out.println(beautify(json));
    }
    
    public static void print(Document doc) {
        System.out.println(beautify(doc.toJson()));
    }

}
