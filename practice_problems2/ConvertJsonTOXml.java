package com.tit.day2_json.practice_problems2;
import org.json.JSONObject;
import org.json.XML;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ConvertJsonTOXml {
    public static void main(String[] args) {
        try {
            //Load the JSON file
            FileInputStream file1 = new FileInputStream(new File("file1.json"));
            String jsonContent1 = new String(file1.readAllBytes());  //Read file content as a string
            System.out.println("Content of file1.json: " + jsonContent1);

            //Parse JSON content into JSONObject
            JSONObject jsonObject = new JSONObject(new JSONTokener(jsonContent1));

            //Convert the JSONObject to XML
            String xmlContent = XML.toString(jsonObject);

            //Write the XML content to a new file
            try (FileWriter file = new FileWriter("file1.xml")) {
                file.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                file.write("<root>\n");
                file.write(xmlContent);
                file.write("\n</root>");
            }

            System.out.println("JSON successfully converted to XML and saved to 'file1.xml'");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.JSONException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
    }
}
