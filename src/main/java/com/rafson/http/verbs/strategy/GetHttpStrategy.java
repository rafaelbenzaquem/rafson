package com.rafson.http.verbs.strategy;

import com.rafson.Response;
import com.rafson.ResponseNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.TreeMap;

public class GetHttpStrategy extends HttpMethod {

    private String method;
    private int connectionTimeout;
    private Map<String, String> propertiesMap = new TreeMap<>();

    public GetHttpStrategy() {
        this.method = "GET";
        this.connectionTimeout = 2000;
        propertiesMap.put("Content-type", "application/json;charset=UTF-8");
        propertiesMap.put("Accept", "application/json");
    }

    public GetHttpStrategy(int connectionTimeout) {
        this.method = "GET";
        this.connectionTimeout = connectionTimeout;
        propertiesMap.put("Content-type", "application/json;charset=UTF-8");
        propertiesMap.put("Accept", "application/json");
    }

    public GetHttpStrategy(int connectionTimeout, Map<String, String> propertiesMap) {
        this.method = "GET";
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
        response.setBody(getBody(connection.getInputStream()));
        System.out.println("GET HTTP CODE " + connection.getResponseCode());
        response.setHeader(connection.getHeaderFields());
        return response;
    }
}
