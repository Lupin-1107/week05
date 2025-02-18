package org.example.advance_problems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCSVData {

    public static void main(String[] args) {
        String csvFile = "employees.csv";

        //Patterns for validation
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Pattern phonePattern = Pattern.compile("^\\d{10}$");

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine;
            boolean isFirstRow = true;
            while ((nextLine = reader.readNext()) != null) {
                //Skip the header row
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                if (nextLine.length < 6) continue;  //Skip rows with insufficient data

                String email = nextLine[4];
                String phoneNumber = nextLine[5].trim();

                //Print email and phone number to check what we're validating
                System.out.println("Email: " + email);
                System.out.println("Phone number: " + phoneNumber);

                boolean isValid = true;

                //Validate email
                Matcher emailMatcher = emailPattern.matcher(email);
                if (!emailMatcher.matches()) {
                    System.out.println("Invalid email: " + email);
                    isValid = false;
                }

                //Validate phone number
                Matcher phoneMatcher = phonePattern.matcher(phoneNumber);
                if (!phoneMatcher.matches()) {
                    System.out.println("Invalid phone number: " + phoneNumber);
                    isValid = false;
                }
                if (!isValid) {
                    System.out.println("Invalid row: " + String.join(",", nextLine));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
