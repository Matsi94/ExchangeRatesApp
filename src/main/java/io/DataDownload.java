package io;

import app.Currency;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataDownload {

    public static void getBuyRate(Currency currency) throws Exception {
        String urlName = "http://api.nbp.pl/api/exchangerates/rates/c/" + currency.name().toLowerCase() +
                "/?format=json";
        String stringJsonObject = null;
        try {
            stringJsonObject = connectToFile(urlName);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        JSONObject jsonObject = new JSONObject(stringJsonObject);
        JSONArray rates = jsonObject.getJSONArray("rates");
        for (int i = 0; i < rates.length(); i++)
        {
            double rate = rates.getJSONObject(i).getDouble("bid");
            System.out.println("Buy rate is: " + rate);
        }
    }

    public static void getSellRate(Currency currency) throws Exception{
        String urlName = "http://api.nbp.pl/api/exchangerates/rates/c/" + currency.name().toLowerCase() +
                "/?format=json";
        String stringJsonObject = null;
        try {
            stringJsonObject = connectToFile(urlName);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        JSONObject jsonObject = new JSONObject(stringJsonObject);
        JSONArray rates = jsonObject.getJSONArray("rates");
        for (int i = 0; i < rates.length(); i++)
        {
            double rate = rates.getJSONObject(i).getDouble("ask");
            System.out.println("Sell rate is: " + rate);
        }
    }

    private static String connectToFile(String urlName) throws IOException {
        URL url = new URL(urlName);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
