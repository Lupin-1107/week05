package com.tit.day2_json.practice_problems2;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVToJson {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader(new File("data.csv"));
            CSVReader csvReader = new CSVReader(fileReader);

            //Read the header
            List<String[]> rows = csvReader.readAll();
            String[] headers = rows.get(0);

            //Create a JSON array to hold the JSON objects
            JSONArray jsonArray = new JSONArray();

            //Iterate over the remaining rows in the CSV
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                JSONObject jsonObject = new JSONObject();

                for (int j = 0; j < headers.length; j++) {
                    jsonObject.put(headers[j], row[j]);
                }

                //Add the JSON object to the JSON array
                jsonArray.put(jsonObject);
            }

            //Write the JSON array to a new file
            try (FileWriter fileWriter = new FileWriter("data.json")) {
                fileWriter.write(jsonArray.toString(4));
            }

            System.out.println("CSV successfully converted to JSON and saved to 'data.json'");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
