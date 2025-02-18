package com.tit.day1_csv.basic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCSVFile {
    public static void main(String[] args) throws IOException {
        String filePath = "employee.csv";
//        File c=new File(filePath);
//        c.createNewFile();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.append("101,Amit,IT,850\n");
            writer.append("102,Sujeet,IT,8500\n");
            writer.append("101,Shiv,AIML,940\n");
            writer.append("102,Shankar,CSE,1200\n");
            writer.append("102,Ankit,CSE,1400\n");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
