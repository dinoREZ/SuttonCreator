package org.example.dataModels.database.hibernate.operations;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.example.Query;
import org.example.QueryParameter;
import org.example.dataModels.DataModel;

import java.util.Objects;
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
                        .getQueryParameters()
                        .stream()
                        .map(QueryParameter::getName)
                        .map(StringUtils::capitalize)
                        .collect(Collectors.joining())
                + "Operation.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryOperation that = (QueryOperation) o;
        return Objects.equals(name, that.name) && Objects.equals(query, that.query) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, query, basePackage);
    }
}
