package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resource {
    String name;
    private final Map<String, String> attributes;
    private final Map<String, Link> links;
    private final List<Query> queries;
    private final List<Resource> subResources;

    public Resource() {
        attributes = new HashMap<>();
        links = new HashMap<>();
        queries = new ArrayList<>();
        subResources = new ArrayList<>();
    }
}
