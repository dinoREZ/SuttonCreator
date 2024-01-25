package org.example;

import java.util.HashMap;
import java.util.Map;

public class Query {
    Map<String, String> attributes;

    public Query() {
        attributes = new HashMap<>();
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Query addAttribute(String type, String name) {
        attributes.put(name, type);
        return this;
    }
}
