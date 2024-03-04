package org.example.dataModels.database.hibernate.models;

import org.example.dataModels.DataModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ModelDB implements DataModel {

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

    @Override
    public String getOutputName() {
        return this.getName() + "DB.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelDB modelDB = (ModelDB) o;
        return Objects.equals(name, modelDB.name) && Objects.equals(attributes, modelDB.attributes) && Objects.equals(basePackage, modelDB.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attributes, basePackage);
    }
}
