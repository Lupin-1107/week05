package com.tit.day2_json;


import org.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;


class Student{
    private String name;
    private int age;
    private String [] subjects;

    public Student(String name, int age, String[] subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String[] getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", age=" + age +
                ", subjects=" + Arrays.toString(subjects)
                ;
    }
}
public class CreateJsonObject {

    public static void main(String[] args) {

//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("Name","Amit");
//        jsonObj.put("Age",22);
//        JSONArray subjects = new JSONArray();
//        subjects.put("Maths");
//        subjects.put("Physics");
//        subjects.put("Chemistry");
//        jsonObj.put("Subjects",subjects);
//
//        System.out.println(jsonObj);





        JSONObject jsonObj1 = new JSONObject();
        String [] subjects = {"Maths","Physics","Chemistry"};
        Student student = new Student("Amit",22,subjects);
        jsonObj1.put("Student1",student);
        System.out.println(jsonObj1);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(student);
            System.out.println("JSON String: " + jsonString);

            // If you want to put it inside a JSONObject
//            JSONObject jsonObj = new JSONObject();
//            jsonObj.put("Student1", new JSONObject(jsonString));
//            System.out.println("JSONObject: " + jsonObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
