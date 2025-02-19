package com.tit.day2_json.practice_problems2;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.ArrayList;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class JavaObjectsIntoJsonArray {
    public static void main(String[] args) throws Exception {
        //Creating a list of Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("John Doe", 30));
        people.add(new Person("Jane Smith", 25));

        //Initialize the ObjectMapper for Jackson
        ObjectMapper objectMapper = new ObjectMapper();

        //Convert the list to a JSON array
        String jsonArray = objectMapper.writeValueAsString(people);

        //Print the JSON array
        System.out.println(jsonArray);
    }
}
