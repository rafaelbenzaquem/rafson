package com.rafson.http.verbs.strategy;

import com.rafson.Response;
import com.rafson.ResponseNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostHttpStrategy extends HttpMethod {

    private String method;
    private int connectionTimeout;
    private Map<String, String> propertiesMap = new TreeMap<>();
    private String body;

    public PostHttpStrategy() {
        this.method = "POST";
       this.connectionTimeout = 2000;
        propertiesMap.put("Content-type", "application/json;charset=UTF-8");
        propertiesMap.put("Accept", "application/json");
    }

    public PostHttpStrategy(String body) {
        this.method = "POST";
        this.body = body;
        this.connectionTimeout = 2000;
        propertiesMap.put("Content-type", "application/json;charset=UTF-8");
        propertiesMap.put("Accept", "application/json");
    }

    public PostHttpStrategy(String body, int connectionTimeout) {
        this.method = "POST";
        this.body = body;
        this.connectionTimeout = connectionTimeout;
        propertiesMap.put("Content-type", "application/json;charset=UTF-8");
        propertiesMap.put("Accept", "application/json");
    }

    public PostHttpStrategy(String body, int connectionTimeout, Map<String, String> propertiesMap) {
        this.method = "POST";
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
        if(body!=null)
        setBody(body, connection.getOutputStream());
        response.setHeader(connection.getHeaderFields());
        try {
            response.setBody(getBody(connection.getInputStream()));
        } catch (IOException  ex) {

            Logger.getLogger(PostHttpStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
