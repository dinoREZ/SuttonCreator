package org.example.database.inmemory;

import org.example.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoImpl {

    private String name;
    private final List<Query> queries;
    private String basePackage;

    public DaoImpl() {
        this.queries = new ArrayList<>();
    }

    public DaoImpl(String name, List<Query> queries, String basePackage) {
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
