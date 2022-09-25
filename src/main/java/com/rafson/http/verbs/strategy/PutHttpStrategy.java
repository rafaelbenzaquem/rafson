package com.rafson.http.verbs.strategy;

import com.rafson.Response;
import com.rafson.ResponseNull;

import java.io.IOException;
import java.net.HttpURLConnection;

public class PutHttpStrategy extends HttpMethod {


    private final String body;

    private PutHttpStrategy(Builder builder) {
        super(builder);
        this.body = builder.body;
    }

    public static Builder builder(){
        return new Builder()
                .setConnectionTimeout(2000)
                .putProperty("Content-type", "application/json;charset=UTF-8")
                .putProperty("Accept", "application/json");
    }

    @Override
    public String getMethod() {
        return METHOD_PUT;
    }

    @Override
    public Response strategyConnectionMethod(HttpURLConnection connection) {
        Response response = new ResponseNull();
        String[] exceptionMessage = null;
        try {
            setBody(body, connection.getOutputStream());
        } catch (IOException e) {
            exceptionMessage = e.toString().split(":");
        }
        response.setHeader(connection.getHeaderFields());
        if (exceptionMessage != null) {
            response.putInHeader("exception", exceptionMessage);
        }
        return response;
    }

    public static class Builder extends HttpMethod.Builder<Builder> {

        private String body;

        public Builder body(String body) {
            this.body = body;
            return getThis();
        }

        @Override
        public PutHttpStrategy build() {
            return new PutHttpStrategy(this);
        }

        @Override
        Builder getThis() {
            return this;
        }
    }
}
