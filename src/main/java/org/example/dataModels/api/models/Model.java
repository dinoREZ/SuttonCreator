package org.example.dataModels.api.models;

import org.example.Link;
import org.example.dataModels.DataModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Model implements DataModel {

    private String name;
    private final Map<String, String> attributes;
    private final Map<String, Link> links;
    private String basePackage;

    public Model() {
        attributes = new HashMap<>();
        links = new HashMap<>();
    }

    public Model(String name, Map<String, String> attributes, Map<String, Link> links, String basePackage) {
        this.name = name;
        this.attributes = attributes;
        this.links = links;
        this.basePackage = basePackage;
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

    @Override
    public String getOutputName() {
        return this.getName() + ".java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(name, model.name) && Objects.equals(attributes, model.attributes) && Objects.equals(links, model.links) && Objects.equals(basePackage, model.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attributes, links, basePackage);
    }
}
