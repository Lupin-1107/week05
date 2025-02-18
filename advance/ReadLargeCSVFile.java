package com.tit.day1_csv.advance;
import java.io.*;
import java.util.*;

public class ReadLargeCSVFile {

    public static void main(String[] args) {
        String filePath = "large_file.csv";
        int chunkSize = 100;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int recordCount = 0;
            List<String> chunk = new ArrayList<>(chunkSize);

            while ((line = br.readLine()) != null) {
                chunk.add(line); //Add the current line to the chunk

                if (chunk.size() == chunkSize) {
                    //Process the chunk
                    processChunk(chunk, recordCount);

                    //Clear the chunk for the next batch
                    chunk.clear();
                }
            }

            //Process any remaining lines
            if (!chunk.isEmpty()) {
                processChunk(chunk, recordCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processChunk(List<String> chunk, int recordCount) {

        System.out.println("Processing " + chunk.size() + " records...");

        //Update the count and print out the progress
        recordCount += chunk.size();
        System.out.println("Total records processed: " + recordCount);

        for (String record : chunk) {

            System.out.println(record);
        }
    }
}
