package org.example.dataModels.api.queries;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.example.QueryParameter;
import org.example.dataModels.DataModel;

import java.util.Objects;
import java.util.stream.Collectors;

public class Query implements DataModel {

    String name;
    org.example.Query query;
    String basePackage;

    public Query(String name, org.example.Query query, String basePackage) {
        this.name = name;
        this.query = query;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public org.example.Query getQuery() {
        return query;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return name + "By" + query.getQueryParameters().stream().map(QueryParameter::getName).map(StringUtils::capitalize).collect(Collectors.joining()) + "Query.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query1 = (Query) o;
        return Objects.equals(name, query1.name) && Objects.equals(query, query1.query) && Objects.equals(basePackage, query1.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, query, basePackage);
    }
}
