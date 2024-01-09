package org.example.database.hibernate;

import org.example.Link;

import java.util.HashMap;
import java.util.Map;

public class DaoAdapter {

    private String name;
    private final Map<String, String> attributes;
    private boolean hasQuery;
    private final Map<String, String> queryAttributes;
    private String basePackage;

    public DaoAdapter() {
        attributes = new HashMap<>();
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
