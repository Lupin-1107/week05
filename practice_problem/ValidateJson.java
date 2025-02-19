package com.tit.day2_json.practice_problem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonParseException;
import java.io.IOException;

public class ValidateJson {
    public static void main(String[] args) {
        //JSON string to be validated
        String json = "{\"name\":\"Alice\",\"email\":\"alice@example.com\",\"age\":25}";

        //Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            //deserialize the JSON into a Person object
            Person person = objectMapper.readValue(json, Person.class);

            //If deserialization succeeds, print the person details
            System.out.println("Valid JSON Structure!");
            System.out.println("Name: " + person.getName());
            System.out.println("Email: " + person.getEmail());
            System.out.println("Age: " + person.getAge());

        } catch (JsonParseException e) {
            System.out.println("Invalid JSON format: " + e.getMessage());
        } catch (JsonMappingException e) {
            //This exception is thrown when the JSON does not match the expected structure
            System.out.println("JSON structure is invalid: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error processing the JSON: " + e.getMessage());
        }
    }
}



 class Person {
    private String name;
    private String email;
    private int age;

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
