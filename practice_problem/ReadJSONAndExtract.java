package com.tit.day2_json.practice_problem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;


public class ReadJSONAndExtract {
    public static void main(String[] args)throws IOException, ClassCastException {

        FileReader reader = new FileReader("employee.json");
        JsonElement jsonElement =JsonParser.parseReader(reader);
        JsonObject obj = jsonElement.getAsJsonObject();


        String name = obj.get("Name").getAsString();
        String email = obj.get("Email").getAsString();
        System.out.println(name +"\n"+email);

//        try
//        {
//
//            ObjectMapper objMap = new ObjectMapper();
//            Object obj = objMap.readValue(new File("employee.json"),)
//
//
//        }catch(Exception e){
//
//            System.out.println(e.getMessage());
//        }
    }

}
