package com.rafson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rafson {


    public String get(String uri) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(uri);
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

    public Map<String, List<String>> post(String uri, String jsonInput) {
        Map<String,List<String>> map = new HashMap<>();
        URL url = null;
        try {
            url = new URL(uri);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (url != null) {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException ex) {
                Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (connection != null) {
                try {
                    connection.setRequestMethod("POST");
                } catch (ProtocolException ex) {
                    Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
                }

                connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);

                try {
                    connection.connect();
                    try (OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream(), "UTF-8")) {

                        os.write(jsonInput);
                        os.flush();
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
                }

                map = connection.getHeaderFields();
            }
        }
        return map;
    }

    public Map<String, List<String>> put(String uri, String jsonInput) {
        Map<String,List<String>> map = new HashMap<>();
        URL url = null;
        try {
            url = new URL(uri);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (url != null) {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException ex) {
                Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (connection != null) {
                try {
                    connection.setRequestMethod("PUT");
                } catch (ProtocolException ex) {
                    Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
                }

                connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);

                try {
                    connection.connect();
                    try (OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream(), "UTF-8")) {

                        os.write(jsonInput);
                        os.flush();
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
                }

                map = connection.getHeaderFields();
            }
        }
        return map;
    }

    public Map<String, List<String>> head(String spec) {
        Map<String, List<String>> headerFields = new TreeMap<>();
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
            headerFields = connection.getHeaderFields();
            connection.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return headerFields;
    }
}
