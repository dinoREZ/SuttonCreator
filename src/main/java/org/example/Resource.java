package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resource {
    private String name;
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

    public String getName() {
        return name;
    }

    public Resource setName(String name) {
        this.name = name;
        return this;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Resource addAttribute(String type, String name) {
        attributes.put(name, type);
        return this;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

    public Resource addLink(String name, Link link) {
        links.put(name, link);
        return this;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public Resource addQuery(Query query) {
        queries.add(query);
        return this;
    }

    public List<Resource> getSubResources() {
        return subResources;
    }

    public Resource addSubResource(Resource subResource) {
        subResources.add(subResource);
        return this;
    }
}
