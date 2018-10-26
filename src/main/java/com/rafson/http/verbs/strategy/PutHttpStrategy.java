package com.rafson.http.verbs.strategy;

import com.rafson.Response;
import com.rafson.ResponseNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.TreeMap;

public class PutHttpStrategy extends HttpMethod {

    private String method;
    private int connectionTimeout;
    private Map<String, String> propertiesMap = new TreeMap<>();
    private String body;

    public PutHttpStrategy(String body) {
        this.method = "PUT";
        this.body = body;
        this.connectionTimeout = 2000;
        propertiesMap.put("Content-type", "application/json;charset=UTF-8");
        propertiesMap.put("Accept", "application/json");
    }

    public PutHttpStrategy(String body, int connectionTimeout) {
        this.method = "PUT";
        this.body = body;
        this.connectionTimeout = connectionTimeout;
        propertiesMap.put("Content-type", "application/json;charset=UTF-8");
        propertiesMap.put("Accept", "application/json");
    }

    public PutHttpStrategy(String body, int connectionTimeout, Map<String, String> propertiesMap) {
        this.method = "PUT";
        this.body = body;
        this.connectionTimeout = connectionTimeout;
        this.propertiesMap = propertiesMap;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    @Override
    public Map<String, String> getPropertiesMap() {
        return propertiesMap;
    }

    @Override
    public Response strategyVerbMethod(HttpURLConnection connection) throws IOException {
        Response response = new ResponseNull();
        setBody(body, connection.getOutputStream());
        System.out.println("PUT HTTP CODE " + connection.getResponseCode());
        response.setHeader(connection.getHeaderFields());
        return response;
    }
}
