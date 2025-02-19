package com.tit.day2_json.practice_problems2;
import org.everit.json.schema.*;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ValidateEmail {

    public static void main(String[] args) {
        try {
            //Load the schema file
            File schemaFile = new File("schemaFile.json");
            JSONObject schemaObject = new JSONObject(new JSONTokener(new FileInputStream(schemaFile)));
            Schema schema = SchemaLoader.load(schemaObject);

            //Load the JSON file to validate
            File jsonFile = new File("file3.json");
            JSONObject jsonData = new JSONObject(new JSONTokener(new FileInputStream(jsonFile)));

            //Validate the JSON data against the schema
            schema.validate(jsonData);

            System.out.println("JSON is valid!");

        } catch (ValidationException e) {
            //If validation fails, print the validation error
            System.out.println("Validation failed: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
