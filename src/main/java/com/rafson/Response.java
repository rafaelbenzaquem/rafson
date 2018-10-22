package com.rafson;

import java.util.List;
import java.util.Map;

public class Response {

    private String body;
    private Map<String, List<String>> header;

    public Response(String body, Map<String, List<String>> header) {
        this.body = body;
        this.header = header;
    }

    public Map<String, List<String>> getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }
}
