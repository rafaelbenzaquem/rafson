package com.rafson.http.verbs.strategy;

import com.rafson.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public abstract class HttpMethod {

    public abstract String getMethod();

    public abstract Map<String,String> getPropertiesMap();

    public abstract int getConnectionTimeout();

    public abstract Response strategyVerbMethod(HttpURLConnection connection) throws IOException;

    protected String getBody(InputStream inputStream) {
        StringBuilder bodyBuilder = new StringBuilder();
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        while (scanner.hasNext()) {
            bodyBuilder.append(scanner.nextLine());
        }
        return bodyBuilder.toString();
    }

    protected void setBody(String body, OutputStream outputStream) throws IOException {
        OutputStreamWriter os = new OutputStreamWriter(outputStream, "UTF-8");
        os.write(body);
        os.flush();
        os.close();
    }
}
