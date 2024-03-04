package org.example.dataModels.database.hibernate;

import org.example.Query;
import org.example.dataModels.DataModel;

import java.util.*;

public class DaoAdapter implements DataModel {

    private String name;
    private final Map<String, String> attributes;
    private final List<Query> queries;
    private String basePackage;

    public DaoAdapter() {
        attributes = new HashMap<>();
        this.queries = new ArrayList<>();
    }

    public DaoAdapter(String name, Map<String, String> attributes, List<Query> queries, String basePackage) {
        this.name = name;
        this.attributes = attributes;
        this.queries = queries;
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

    public List<Query> getQueries() {
        return queries;
    }

    public void addQuery(Query query) {
        queries.add(query);
    }


    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public String getOutputName() {
        return this.getName() + "DaoAdapter.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DaoAdapter that = (DaoAdapter) o;
        return Objects.equals(name, that.name) && Objects.equals(attributes, that.attributes) && Objects.equals(queries, that.queries) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attributes, queries, basePackage);
    }
}
