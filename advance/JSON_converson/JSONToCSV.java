package com.tit.day1_csv.advance.JSON_converson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;

public class JSONToCSV {

    public static void main(String[] args) {
        // Example file paths
        String jsonFile = "students.json";
        String csvFile = "students.csv";
        String csvInputFile = "students.csv";
        String jsonOutputFile = "students_output.json";

        //Convert JSON to CSV
        try {
            jsonToCsv(jsonFile, csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert CSV back to JSON
        try {
            csvToJson(csvInputFile, jsonOutputFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to convert JSON to CSV
    public static void jsonToCsv(String jsonFile, String csvFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Student> students = Arrays.asList(objectMapper.readValue(new File(jsonFile), Student[].class));

        //Create CSVWriter to write to a file
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            //Write CSV header
            String[] header = {"ID", "Name", "Age", "Grade"};
            writer.writeNext(header);

            //Write student data
            for (Student student : students) {
                String[] studentData = {String.valueOf(student.getId()), student.getName(), String.valueOf(student.getAge()), student.getGrade()};
                writer.writeNext(studentData);
            }

            System.out.println("Converted JSON to CSV: " + csvFile);
        }
    }

    //Method to convert CSV to JSON
    public static void csvToJson(String csvFile, String jsonFile) throws IOException, CsvValidationException {
        //Read CSV file
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            //Read the header
            String[] header = reader.readNext();

            List<Student> students = new ArrayList<>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                int id = Integer.parseInt(nextLine[0]);
                String name = nextLine[1];
                int age = Integer.parseInt(nextLine[2]);
                String grade = nextLine[3];

                students.add(new Student(id, name, age, grade));
            }

            //Write the list of students to a JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFile), students);

            System.out.println("Converted CSV to JSON: " + jsonFile);
        }
    }

    //Student class to hold student data
    public static class Student {
        private int id;
        private String name;
        private int age;
        private String grade;

        public Student() {}

        public Student(int id, String name, int age, String grade) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }
}
