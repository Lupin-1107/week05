package com.tit.day2_json.practice_problem;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class ListObjectIntoJsonArray {
    public static void main(String[] args) {
        //Create a list of Person objects
        List<Person1> people = new ArrayList<>();
        people.add(new Person1("Alice", "alice@example.com", 25));
        people.add(new Person1("Bob", "bob@example.com", 30));
        people.add(new Person1("Charlie", "charlie@example.com", 35));

        //Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonArray = objectMapper.writeValueAsString(people);

            //Print the JSON array
            System.out.println(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


 class Person1 {
    private String name;
    private String email;
    private int age;

    //Constructor
    public Person1(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
