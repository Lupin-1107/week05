package com.tit.day1_csv.advance.merge_twoCSV;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;

public class MergeTwoCSVFile {

    public static void main(String[] args) {
        String filePath1 = "student1.csv";
        String filePath2 = "student2.csv";
        String outputPath = "merged_students.csv";

        //Map to store student data by ID for easy merging
        Map<Integer, Student> studentMap = new HashMap<>();

        try (CSVReader reader1 = new CSVReader(new FileReader(filePath1))) {
            String[] line;
            reader1.readNext(); // Skip the header row

            while ((line = reader1.readNext()) != null) {
                int id = Integer.parseInt(line[0]);
                String name = line[1];
                int age = Integer.parseInt(line[2]);

                //Store in map using ID as key
                studentMap.put(id, new Student(id, name, age, -1, ""));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        //read the second CSV and merge data based on ID
        try (CSVReader reader2 = new CSVReader(new FileReader(filePath2))) {
            String[] line;
            reader2.readNext();

            while ((line = reader2.readNext()) != null) {
                int id = Integer.parseInt(line[0]);
                int marks = Integer.parseInt(line[1]);
                String grade = line[2];

                //If the ID exists in the first CSV file, update the student record
                if (studentMap.containsKey(id)) {
                    Student student = studentMap.get(id);
                    student = new Student(id, student.getName(), student.getAge(), marks, grade);
                    studentMap.put(id, student);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        //Write the merged data to a new CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {
            //Write the header
            writer.writeNext(new String[]{"ID", "Name", "Age", "Marks", "Grade"});

            //write the merged students data
            for (Student student : studentMap.values()) {
                writer.writeNext(new String[]{
                        String.valueOf(student.getId()),
                        student.getName(),
                        String.valueOf(student.getAge()),
                        String.valueOf(student.getMarks()),
                        student.getGrade()
                });
            }

            System.out.println("Merged data written to: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
