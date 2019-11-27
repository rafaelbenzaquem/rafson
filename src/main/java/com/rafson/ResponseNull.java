package com.rafson;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ResponseNull extends Response {
    public ResponseNull() {
        super(new TreeMap<String, List<String>>(), null);
    }
}
