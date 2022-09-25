package com.rafson.http.verbs.strategy;

import com.rafson.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public abstract class HttpMethod {

    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_DELETE = "DELETE";
    public static final String METHOD_HEAD = "HEAD";
    public static final String METHOD_PUT = "PUT";

    public static final String METHOD_CONNECT = "CONNECT";
    public static final String METHOD_OPTIONS = "OPTIONS";
    public static final String METHOD_TRACE = "TRACE";

    private final int connectionTimeout;
    private final Map<String, String> headerPropertiesMap;

    HttpMethod(Builder<?> builder) {
        this.connectionTimeout = builder.connectionTimeout;
        this.headerPropertiesMap = builder.headerPropertiesMap;
    }

    public abstract String getMethod();

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public Map<String, String> getHeaderPropertiesMap() {
        return headerPropertiesMap;
    }

    public abstract Response strategyConnectionMethod(HttpURLConnection connection);

    protected String getBody(InputStream inputStream) {
        StringBuilder bodyBuilder = new StringBuilder();
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        while (scanner.hasNext()) {
            bodyBuilder.append(scanner.nextLine());
        }
        return bodyBuilder.toString();
    }

    protected void setBody(String body, OutputStream outputStream) throws IOException {
        OutputStreamWriter os = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        os.write(body);
        os.flush();
        os.close();
    }

    abstract static class Builder<T extends Builder<T>> {
        private int connectionTimeout;
        private Map<String, String> headerPropertiesMap = new TreeMap<>();

        public T setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return getThis();
        }

        public T setHeaderPropertiesMap(Map<String, String> headerPropertiesMap) {
            this.headerPropertiesMap = headerPropertiesMap;
            return getThis();
        }

        public T putProperty(String keyProperty, String propertyValue) {
            this.headerPropertiesMap.put(keyProperty, propertyValue);
            return getThis();
        }

        public abstract HttpMethod build();

        abstract T getThis();
    }
}
