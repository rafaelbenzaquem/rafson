package com.rafson;

import com.rafson.http.verbs.strategy.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rafson {


    public Response get(String uri) {
        Response response = templateHttpMethod(uri, new GetHttpStrategy());
        return response;
    }

    public Response get(String uri, String body) {
        Response response = templateHttpMethod(uri, new GetHttpStrategy(body));
        return response;
    }

    public Response post(String uri) {
        Response response = templateHttpMethod(uri, new PostHttpStrategy());
        return response;
    }

    public Response post(String uri, String body) {
        Response response = templateHttpMethod(uri, new PostHttpStrategy(body));
        return response;
    }

    public Response put(String uri) {
        Response response = templateHttpMethod(uri, new PutHttpStrategy());
        return response;
    }

    public Response put(String uri, String body) {
        Response response = templateHttpMethod(uri, new PutHttpStrategy(body));
        return response;
    }

    public Response head(String uri) {
        Response response = templateHttpMethod(uri, new HeadHttpStrategy());
        return response;
    }

    public Response delete(String uri) {
        Response response = templateHttpMethod(uri, new DeleteHttpStrategy());
        return response;
    }

    private Response templateHttpMethod(String uri, HttpMethod iHttpMethod) {
        Response response = new ResponseNull();
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(iHttpMethod.getMethod());
            for (String key : iHttpMethod.getPropertiesMap().keySet()) {
                connection.setRequestProperty(key, iHttpMethod.getPropertiesMap().get(key));
            }
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(iHttpMethod.getConnectionTimeout());

            connection.connect();
            response = iHttpMethod.strategyVerbMethod(connection);
            connection.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
