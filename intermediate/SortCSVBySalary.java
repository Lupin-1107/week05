package com.tit.day1_csv.intermediate;

import java.io.*;
import java.util.*;

public class SortCSVBySalary {
    public static void main(String[] args) {
        String inputFilePath = "employees.csv";
        List<String[]> records = new ArrayList<>();
        String header = "";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line;
                    isFirstLine = false;
                    continue;
                }
                records.add(line.split(","));
            }

            records.sort((a, b) -> Double.compare(Double.parseDouble(b[2].trim()), Double.parseDouble(a[2].trim())));

            System.out.println(header);
            for (int i = 0; i < Math.min(5, records.size()); i++) {
                System.out.println(String.join(",", records.get(i)));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid salary format encountered.");
        }
    }
}
