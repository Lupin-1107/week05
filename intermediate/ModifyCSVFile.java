package com.tit.day1_csv.intermediate;

import java.io.*;

public class ModifyCSVFile {
    public static void main(String[] args) {
        String inputFilePath = "employees.csv";
        String outputFilePath = "updated_employees.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) { // Write header
                    bw.write(line);
                    bw.newLine();
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 3) { // Ensure correct format
                    try {
                        String department = data[1].trim();
                        double salary = Double.parseDouble(data[2].trim());

                        if ("IT".equalsIgnoreCase(department)) {
                            salary *= 1.10; // Increase salary by 10%
                        }

                        data[2] = String.format("%.2f", salary);
                        bw.write(String.join(",", data));
                        bw.newLine();
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid salary in record: " + line);
                    }
                }
            }

            System.out.println("Updated CSV file saved as " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}
