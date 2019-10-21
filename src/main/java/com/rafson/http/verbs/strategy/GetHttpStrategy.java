package com.rafson.http.verbs.strategy;

import com.rafson.Rafson;
import com.rafson.Response;
import com.rafson.ResponseNull;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetHttpStrategy extends HttpMethod {

    private String method;
    private int connectionTimeout;
    private Map<String, String> propertiesMap = new TreeMap<>();
    private String body;


    public GetHttpStrategy() {
        this.method = "GET";
        this.connectionTimeout = 2000;
        propertiesMap.put("Content-type", "application/json;charset=UTF-8");
        propertiesMap.put("Accept", "application/json");
    }

    public GetHttpStrategy(String body) {
        this.method = "GET";
        this.body = body;
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
        if(body!=null)
        setBody(body, connection.getOutputStream());
        response.setHeader(connection.getHeaderFields());
        try {
            response.setBody(getBody(connection.getInputStream()));
        } catch (IOException  ex) {
            
            Logger.getLogger(GetHttpStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
