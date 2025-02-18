package com.tit.day1_csv.advance;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DeleteDuplicateCSV {

    public static void main(String[] args) {
        String inputCsvFile = "large_file.csv";   //Input CSV file
        String outputCsvFile = "cleaned_file.csv";

        try (CSVReader reader = new CSVReader(new FileReader(inputCsvFile));
             CSVWriter writer = new CSVWriter(new FileWriter(outputCsvFile))) {

            String[] nextLine;
            Set<String> seenIds = new HashSet<>();  //To keep track of unique IDs
            boolean isFirstLine = true;  //Flag to write header only once

            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    writer.writeNext(nextLine);
                    isFirstLine = false;
                    continue;
                }

                String id = nextLine[0];  //Assuming the ID is in the first column

                //If ID is already seen, skip this row (duplicate)
                if (!seenIds.contains(id)) {
                    seenIds.add(id);
                    writer.writeNext(nextLine);
                }
            }

            System.out.println("Duplicates removed and cleaned data saved to: " + outputCsvFile);

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
