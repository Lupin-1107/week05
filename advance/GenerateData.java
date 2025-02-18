package com.tit.day1_csv.advance;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class GenerateData{

    public static void main(String[] args) {
        //Simulating employee data as if it was fetched from a database
        String[][] employeeData = {
                {"1", "John Doe", "Engineering", "75000.00"},
                {"2", "Jane Smith", "HR", "65000.00"},
                {"3", "Paul Brown", "Marketing", "72000.00"},
                {"4", "Anna Johnson", "Engineering", "78000.00"},
                {"5", "Tom Harris", "Sales", "69000.00"}
        };

        //Output CSV file
        String outputCsvFile = "employee_report.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(outputCsvFile))) {

            //Writing header to CSV file
            String[] header = {"Employee ID", "Name", "Department", "Salary"};
            writer.writeNext(header);

            //Writing employee data to CSV file
            for (String[] employee : employeeData) {
                writer.writeNext(employee);
            }

            System.out.println("CSV report generated successfully! Check the file: " + outputCsvFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
