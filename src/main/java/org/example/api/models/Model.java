package org.example.api.models;

import org.example.Link;

import java.util.HashMap;
import java.util.Map;

public class Model {

    private String name;
    private final Map<String, String> attributes;
    private final Map<String, Link> links;
    private String basePackage;

    public Model() {
        attributes = new HashMap<>();
        links = new HashMap<>();
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

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
