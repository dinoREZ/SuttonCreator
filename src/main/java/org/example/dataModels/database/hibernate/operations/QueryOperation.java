package org.example.dataModels.database.hibernate.operations;

import org.apache.commons.lang3.StringUtils;
import org.example.Query;
import org.example.dataModels.DataModel;

import java.util.stream.Collectors;

public class QueryOperation implements DataModel {

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

    @Override
    public String getOutputName() {
        return this.getName() + "QueryBy" +
                this.getQuery()
                        .getAttributes()
                        .keySet()
                        .stream()
                        .map(StringUtils::capitalize)
                        .collect(Collectors.joining())
                + "Operation.java";
    }
}
