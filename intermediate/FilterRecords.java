package com.tit.day1_csv.intermediate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilterRecords {

        public static void main(String[] args) {
            String filePath = "read.csv";

            try(BufferedReader br = new BufferedReader(new FileReader(filePath))){

                boolean isHeader = true;
                String line;

                System.out.printf("%-5s %-7s %-5s %-5s%n","ID","Name","Age","Marks");

                while((line = br.readLine())!= null) {
                    if (isHeader) {
                        isHeader = false;
                        continue;
                    }
                    String[] data = line.split(",");
                    if (Integer.parseInt(data[3]) > 80) {
                        System.out.printf("%-5s %-7s %-5s %-5s%n", data[0], data[1], data[2], data[3]);
                    }
                }
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

