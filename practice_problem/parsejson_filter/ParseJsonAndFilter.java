package com.tit.day2_json.practice_problem.parsejson_filter;
import org.json.JSONArray;
import org.json.JSONObject;

    public class ParseJsonAndFilter{
        public static void main(String[] args) {
            //Sample JSON data (string representation)
            String jsonString = "["
                    + "{\"name\":\"Alice\", \"age\":30},"
                    + "{\"name\":\"Bob\", \"age\":24},"
                    + "{\"name\":\"Charlie\", \"age\":28},"
                    + "{\"name\":\"David\", \"age\":22}"
                    + "]";

            //Parse the JSON string into a JSONArray
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONArray filteredArray = new JSONArray();

            // Iterate through each record and filter by age > 25
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject person = jsonArray.getJSONObject(i);
                int age = person.getInt("age"); // Get age value
                if (age > 25) {
                    filteredArray.put(person);
                }
            }

            //Output the filtered JSON data in a readable format
            System.out.println(filteredArray.toString(2));
        }
    }
