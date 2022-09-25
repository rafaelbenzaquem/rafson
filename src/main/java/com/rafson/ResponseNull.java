package com.rafson;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ResponseNull extends Response {
    public ResponseNull() {
        super(createMapNull(), "");
    }

    private static Map<String, List<String>> createMapNull() {
        Map<String, List<String>> map = new TreeMap<>();
        map.put("code", Collections.singletonList("HTTP/1.1 404 Not Found"));
        return map;
    }
}
