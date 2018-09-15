package com.rafson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rafson {


    public String get(String spec) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(spec);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(2000);
            connection.connect();
            System.out.println("GET HTTP CODE" + connection.getResponseCode());
            Scanner scanner = new Scanner(url.openStream(), "UTF-8");
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            connection.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response.toString();
    }
}
