package com.rafson.http.verbs.strategy;

import com.rafson.Response;
import com.rafson.ResponseNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.TreeMap;

public class DeleteHttpStrategy extends HttpMethod {

    private String method;
    private int connectionTimeout;
    private Map<String, String> propertiesMap = new TreeMap<>();

    public DeleteHttpStrategy() {
        this.method = "DELETE";
        this.connectionTimeout = 2000;
        propertiesMap.put("Content-type", "application/json;charset=UTF-8");
        propertiesMap.put("Accept", "application/json");
    }

    public DeleteHttpStrategy(int connectionTimeout) {
        this.method = "DELETE";
        this.connectionTimeout = connectionTimeout;
        propertiesMap.put("Content-type", "application/json;charset=UTF-8");
        propertiesMap.put("Accept", "application/json");
    }

    public DeleteHttpStrategy(int connectionTimeout, Map<String, String> propertiesMap) {
        this.method = "DELETE";
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
    public Response strategyVerbMethod(HttpURLConnection connection) {
        Response response = new ResponseNull();
        String exceptionMessage[] = null;
        try {
            System.out.println("DELETE HTTP CODE " + connection.getResponseCode());
        } catch (Exception e) {
            exceptionMessage = e.toString().split(":");
        }
        response.setHeader(connection.getHeaderFields());
        if (exceptionMessage != null) {
            response.putInHeader("exception", exceptionMessage);
        }
        return response;
    }
}
