package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataModel {

    private String name;
    private final Map<String, String> attributes;
    private final Map<String, Link> links;
    private boolean hasQuery;
    private final Map<String, String> queryAttributes;
    private String basePackage;

    public DataModel() {
        attributes = new HashMap<>();
        links = new HashMap<>();
        queryAttributes = new HashMap<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addAttribute(String type, String name) {
        attributes.put(name, type);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void addLink(String name, Link link) {
        links.put(name, link);
    }

    public Map<String, Link> getLinks() {
        return links;
    }

    public boolean getHasQuery() {
        return hasQuery;
    }

    public void setHasQuery(boolean hasQuery) {
        this.hasQuery = hasQuery;
    }

    public Map<String, String> getQueryAttributes() {
        return queryAttributes;
    }

    public void addQueryParameter(String type, String name) {
        queryAttributes.put(name, type);
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
