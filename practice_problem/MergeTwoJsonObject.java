package com.tit.day2_json.practice_problem;
import org.json.JSONObject;

public class MergeTwoJsonObject {
    public static void main(String[] args) {
        //Create the first JSON object
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "Alice");
        jsonObject1.put("email", "alice@example.com");

        //Create the second JSON object
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("age", 25);
        jsonObject2.put("address", "123 Main Street");

        //Merge jsonObject2 into jsonObject1
        for (String key : jsonObject2.keySet()) {
            jsonObject1.put(key, jsonObject2.get(key));
        }

        System.out.println(jsonObject1.toString());
    }
}
