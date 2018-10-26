package com.rafson;

import java.util.List;
import java.util.Map;

public class Response {

    private Map<String, List<String>> header;
    private String body;

    public Response(Map<String, List<String>> header, String body) {
        this.body = body;
        this.header = header;
    }

    public Map<String, List<String>> getHeader() {
        return header;
    }

    public void setHeader(Map<String, List<String>> header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
