package com.rafson;

import com.rafson.http.verbs.strategy.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Rafson {

    private Rafson() {
    }

    public static Response get(String uri) {
        return templateHttpMethod(uri, GetHttpStrategy.builder().build());
    }

    public static Response post(String uri, String body) {
        return templateHttpMethod(uri, PostHttpStrategy.builder().body(body).build());
    }

    public static Response put(String uri, String body) {
        return templateHttpMethod(uri, PutHttpStrategy.builder().body(body).build());
    }

    public static Response head(String uri) {
        return templateHttpMethod(uri, HeadHttpStrategy.builder().build());
    }

    public static Response delete(String uri) {
        return templateHttpMethod(uri, DeleteHttpStrategy.builder().build());
    }

    private static Response templateHttpMethod(String uri, HttpMethod iHttpMethod) {
        Response response = new ResponseNull();
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(iHttpMethod.getMethod());
            for (String key : iHttpMethod.getHeaderPropertiesMap().keySet()) {
                connection.setRequestProperty(key, iHttpMethod.getHeaderPropertiesMap().get(key));
            }
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(iHttpMethod.getConnectionTimeout());

            connection.connect();
            response = iHttpMethod.strategyConnectionMethod(connection);
            connection.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(Rafson.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return response;
    }
}
