package com.tit.day2_json.practice_problems2;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MergeTwoJsonFile {
    public static void main(String[] args) {
        try {
            FileInputStream file1 = new FileInputStream(new File("file1.json"));
            String jsonContent1 = new String(file1.readAllBytes());
            System.out.println("Content of file1.json: " + jsonContent1);

            //Load the second JSON file
            FileInputStream file2 = new FileInputStream(new File("file2.json"));
            String jsonContent2 = new String(file2.readAllBytes());
            System.out.println("Content of file2.json: " + jsonContent2);

            //Check if the content of file1.json is an object
            if (jsonContent1.trim().startsWith("{") && jsonContent2.trim().startsWith("[")) {
                //File1 is an object and File2 is an array
                JSONObject object1 = new JSONObject(new JSONTokener(jsonContent1));
                JSONArray array2 = new JSONArray(new JSONTokener(jsonContent2));

                array2.put(object1);

                //Write the merged result to a new file
                try (FileWriter file = new FileWriter("merged.json")) {
                    file.write(array2.toString(4));  //Pretty print with an indentation level of 4
                }

                System.out.println("JSON object and array merged successfully into 'merged.json'");

            } else if (jsonContent1.trim().startsWith("[") && jsonContent2.trim().startsWith("[")) {
                //Both files contain JSON arrays
                JSONArray array1 = new JSONArray(new JSONTokener(jsonContent1));
                JSONArray array2 = new JSONArray(new JSONTokener(jsonContent2));

                //Merge array2 into array1
                for (int i = 0; i < array2.length(); i++) {
                    array1.put(array2.get(i));  // Add elements from array2 to array1
                }

                //Write the merged array to a new file
                try (FileWriter file = new FileWriter("merged.json")) {
                    file.write(array1.toString(4));
                }

                System.out.println("JSON arrays merged successfully into 'merged.json'");

            } else {
                System.err.println("Error: Unsupported JSON structure (file1 or file2 are not in the expected format).");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.JSONException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
    }
}
