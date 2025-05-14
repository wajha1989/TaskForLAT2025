package com.example.taskforlat2025.util;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

public class CurrencyConverterExternal implements CurrencyConverter{

    //Go to application.properties to add your api key
    @Value("${app.api_key}")
    private static String API_KEY;
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    public double convert(double amount, String from, String to){
        if (from.equals(to)){
            return amount;
        }

        String urlString = BASE_URL + from + "/" + to;
        try{
            URL url = new URL(urlString);
            try{
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                Scanner sc = new Scanner(url.openStream());
                StringBuilder response = new StringBuilder();
                while (sc.hasNext()) {
                    response.append(sc.nextLine());
                }
                sc.close();

                JSONObject obj = new JSONObject(response.toString());
                if (obj.getString("result").equals("success")) {
                    double rate = obj.getDouble("conversion_rate");
                    return amount * rate;
                } else {
                    throw new IOException("API error: " + obj.getString("error-type"));
                }

            }catch (IOException e){
                System.out.println("Java IO exception" + e.getMessage());
            }


        } catch (MalformedURLException e){
            System.out.println("Malformed URL " + e.getMessage());
        }
        return -1.0;
    }

}
