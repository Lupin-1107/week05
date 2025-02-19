package com.tit.day2_json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONObject;

class Car {
    private String brand;
    private String model;
    private double price;

    public Car(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }



    // ✅ Jackson needs getters for serialization
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("brand", brand);
        jsonObject.put("model", model);
        jsonObject.put("price", price);


        return jsonObject;
    }
}



public class ConvertJavaObject {
    public static void main(String[] args) {
        Car car1 = new Car("BMW", "X5", 85);
        Car car2 = new Car("Mercedes", "Maybach", 250);
        try {

            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert Java objects to JSON format
            String jsonCar1 = objectMapper.writeValueAsString(car1);
            String jsonCar2 = objectMapper.writeValueAsString(car2);

            // Print the JSON output
            System.out.println("Car 1 JSON: " + jsonCar1);
            System.out.println("Car 2 JSON: " + jsonCar2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gsonObj = new Gson();
        String jsonCar = gsonObj.toJson(car1);
        System.out.println(jsonCar.toString());

        System.out.println(car1.toJSON());
        System.out.println(car2.toJSON());


    }
    }
