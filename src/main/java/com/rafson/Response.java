package com.rafson;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        this.header = removeKeyNull(header);
    }

    private Map<String, List<String>> removeKeyNull(Map<String, List<String>> header) {
        Map<String, List<String>> newHeader = new TreeMap<>();
        for (String key : header.keySet()) {
            if (key == null) {
                newHeader.put("code", header.get(key));
            } else {
                newHeader.put(key, header.get(key));
            }
        }
        return newHeader;
    }

    public void putInHeader(String key, String... att) {
        this.header.put(key, Arrays.asList(att));
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
