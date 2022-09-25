package com.rafson.http.verbs.strategy;

import com.rafson.Response;
import com.rafson.ResponseNull;

import java.io.IOException;
import java.net.HttpURLConnection;

public class DeleteHttpStrategy extends HttpMethod {


    public DeleteHttpStrategy(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder()
                .setConnectionTimeout(2000)
                .putProperty("Content-type", "application/json;charset=UTF-8")
                .putProperty("Accept", "application/json");
    }

    @Override
    public String getMethod() {
        return METHOD_DELETE;
    }

    @Override
    public Response strategyConnectionMethod(HttpURLConnection connection) {
        Response response = new ResponseNull();
        String[] exceptionMessage = null;

        try {
            connection.getResponseCode();
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
        @Override
        public DeleteHttpStrategy build() {
            return new DeleteHttpStrategy(this);
        }

        @Override
        Builder getThis() {
            return this;
        }
    }
}
