package com.tit.day2_json.practice_problems2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class FilterUsers {
    public static void main(String[] args) {
        try {
            //create an ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            //Read the JSON file
            JsonNode users = objectMapper.readTree(new File("file2.json"));

            //Iterate over the users and print only those older than 25
            Iterator<JsonNode> elements = users.elements();
            while (elements.hasNext()) {
                JsonNode user = elements.next();
                int age = user.get("age").asInt();
                if (age > 25) {
                    System.out.println(user.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
