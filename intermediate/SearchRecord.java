package com.tit.day1_csv.intermediate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearchRecord {

    public static void findName(String filePath,String name){

            try(BufferedReader br = new BufferedReader(new FileReader(filePath))){

                String line;

                while((line = br.readLine())!= null) {

                    String[] data = line.split(",");
                    if (data[1].equals(name)) {
                        System.out.printf("Name: %-5s Department: %-7s salary: %-5s%n", data[1], data[2], data[3]);
                        break;
                    }
                }
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
    }
    public static void main(String[] args) {
        String filePath = "read.csv";
        String name = "Amit";

        findName(filePath,name);

    }
}
