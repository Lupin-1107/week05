package org.example.advance_problems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;

    public class CSVDataIntoObjects {
        public static void main(String[] args) {
            String filePath = "students.csv";
            List<Student> students = new ArrayList<>();

            try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
                String[] line;
                reader.readNext();

                //Read each line from the CSV file
                while ((line = reader.readNext()) != null) {
                    //Assuming CSV has Name, Age, Grade as columns
                    String name = line[0];
                    int age = Integer.parseInt(line[1]);
                    String grade = line[2];

                    Student student = new Student(name, age, grade);
                    students.add(student);
                }
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }

            //Print the students in the list
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
