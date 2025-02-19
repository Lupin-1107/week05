package com.tit.day2_json.practice_problems2;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Employee {
    String name;
    int age;
    String city;
    String email;

    public Employee(String name, int age, String city, String email) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.email = email;
    }

    //Convert employee object to JSON object
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("age", age);
        jsonObject.put("city", city);
        jsonObject.put("email", email);
        return jsonObject;
    }
}

public class GenerateReprtJson {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John Doe", 30, "New York", "john.doe@example.com"));
        employees.add(new Employee("Jane Smith", 25, "Los Angeles", "jane.smith@example.com"));
        employees.add(new Employee("Alice Johnson", 28, "San Francisco", "alice.johnson@example.com"));

        //Create a JSON array to hold all employee records
        JSONArray jsonArray = new JSONArray();

        //Convert each employee object to JSON and add it to the JSON array
        for (Employee employee : employees) {
            jsonArray.put(employee.toJson());
        }

        try (FileWriter fileWriter = new FileWriter("report.json")) {
            fileWriter.write(jsonArray.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("JSON report generated successfully and saved to 'report.json'");
    }
}
