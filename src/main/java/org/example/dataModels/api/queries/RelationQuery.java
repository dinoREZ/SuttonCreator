package org.example.dataModels.api.queries;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.example.Query;
import org.example.QueryParameter;
import org.example.dataModels.DataModel;

import java.util.Objects;
import java.util.stream.Collectors;

public class RelationQuery implements DataModel {

    String primaryName;
    String secondaryName;
    Query query;
    String basePackage;

    public RelationQuery(String primaryName, String secondaryName, Query query, String basePackage) {
        this.primaryName = primaryName;
        this.secondaryName = secondaryName;
        this.query = query;
        this.basePackage = basePackage;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public Query getQuery() {
        return query;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return primaryName + secondaryName + "By" + query.getQueryParameters().stream().map(QueryParameter::getName).map(StringUtils::capitalize).collect(Collectors.joining()) + "Query.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationQuery that = (RelationQuery) o;
        return Objects.equals(primaryName, that.primaryName) && Objects.equals(secondaryName, that.secondaryName) && Objects.equals(query, that.query) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryName, secondaryName, query, basePackage);
    }
}
