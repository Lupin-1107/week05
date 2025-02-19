package com.tit.day2_json.practice_problems2;
import org.json.JSONObject;
import org.json.JSONArray;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class PrintKeyValue {
    public static void main(String[] args) {
        try {
            //Read the JSON file content into a string
            String content = new String(Files.readAllBytes(Paths.get("file.json")));

            //Convert the string content into a JSONObject
            JSONObject jsonObject = new JSONObject(content);

            //Call the method to print the keys and values
            printKeysAndValues(jsonObject);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printKeysAndValues(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);

            System.out.println(key + ": " + value);

            //If the value is a JSONObject, call the method recursively
            if (value instanceof JSONObject) {
                printKeysAndValues((JSONObject) value);
            }

            //if the value is a JSONArray, handle each element
            if (value instanceof JSONArray) {
                for (int i = 0; i < ((JSONArray) value).length(); i++) {
                    Object arrayElement = ((JSONArray) value).get(i);
                    if (arrayElement instanceof JSONObject) {
                        printKeysAndValues((JSONObject) arrayElement);
                    } else {
                        System.out.println("  " + arrayElement);
                    }
                }
            }
        }
    }
}
