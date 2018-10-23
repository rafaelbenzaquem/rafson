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


    public Response get(String uri) {
        Map<String, List<String>> header = new TreeMap<>();
        StringBuilder bodyBuilder = new StringBuilder();
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(2000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream(), "UTF-8");
            while (scanner.hasNext()) {
                bodyBuilder.append(scanner.nextLine());
            }

            System.out.println("GET HTTP CODE " + connection.getResponseCode());
            header = connection.getHeaderFields();

            connection.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Response(header, bodyBuilder.toString());
    }

    public Response post(String uri, String jsonInput) {
        Map<String, List<String>> header = new HashMap<>();
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(2000);
            connection.connect();



            OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            os.write(jsonInput);
            os.flush();
            os.close();

            System.out.println("POST HTTP CODE " + connection.getResponseCode());
            header = connection.getHeaderFields();

            connection.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Response(header, null);
    }

    public Response put(String uri, String jsonInput) {
        Map<String, List<String>> header = new HashMap<>();
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.connect();

            OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            os.write(jsonInput);
            os.flush();
            os.close();

            System.out.println("PUT HTTP CODE " + connection.getResponseCode());
            header = connection.getHeaderFields();
            connection.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Response(header, null);
    }

    public Response head(String uri) {
        Map<String, List<String>> header = new TreeMap<>();
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(2000);
            connection.connect();

            System.out.println("HEAD HTTP CODE " + connection.getResponseCode());
            header = connection.getHeaderFields();

            connection.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Response(header, null);
    }

    public Response delete(String uri) {
        Map<String, List<String>> header = new TreeMap<>();
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(2000);
            connection.connect();

            System.out.println("DELETE HTTP CODE " + connection.getResponseCode());
            header = connection.getHeaderFields();

            connection.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Response(header, null);
    }
}
