package com.rafson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseNull extends Response {
    public ResponseNull() {
        super(new HashMap<String, List<String>>(), null); 
        Map<String,List<String>> header = new HashMap<>();
        header.put(null, Arrays.asList("HTTP/1.1 404 Not Found"));
        setHeader(header);
        
    }
}
