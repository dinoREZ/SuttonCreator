package org.example.database.hibernate.models;

import java.util.HashMap;
import java.util.Map;

public class ModelDB {

    private String name;
    private final Map<String, String> attributes;
    private String basePackage;

    public ModelDB() {
        this.attributes = new HashMap<>();
    }

    public ModelDB(String name, Map<String, String> attributes, String basePackage) {
        this.name = name;
        this.attributes = attributes;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void addAttribute(String type, String name) {
        attributes.put(name, type);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
