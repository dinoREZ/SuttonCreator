package org.example.database;

import org.example.Query;

import java.util.ArrayList;
import java.util.List;

public class Dao {

    private String name;
    private final List<Query> queries;
    private String basePackage;

    public Dao() {
        this.queries = new ArrayList<>();
    }

    public Dao(String name, List<Query> queries, String basePackage) {
        this.name = name;
        this.queries = queries;
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

    public List<Query> getQueries() {
        return queries;
    }

    public void addQuery(Query query) {
        queries.add(query);
    }
}
