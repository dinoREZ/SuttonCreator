package org.example.dataModels.database.hibernate.operations;

import org.example.Query;

public class QueryOperation {

    private String name;
    private Query query;
    private String basePackage;

    public QueryOperation() {
    }

    public QueryOperation(String name, Query query, String basePackage) {
        this.name = name;
        this.query = query;
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

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}