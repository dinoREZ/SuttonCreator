package org.example.dataModels.database.hibernate.operations;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.example.Query;
import org.example.dataModels.DataModel;

import java.util.Objects;
import java.util.stream.Collectors;

public class RelationQueryOperation implements DataModel {

    String primaryName;
    String secondaryName;
    String basePackage;
    Query query;

    public RelationQueryOperation() {
    }

    public RelationQueryOperation(String primaryName, String secondaryName, String basePackage, Query query) {
        this.primaryName = primaryName;
        this.secondaryName = secondaryName;
        this.basePackage = basePackage;
        this.query = query;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public void setSecondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
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
        return this.getPrimaryName() + this.getSecondaryName() + "QueryBy" +
                this.getQuery()
                        .getAttributes()
                        .stream()
                        .map(Triple::getMiddle)
                        .map(StringUtils::capitalize)
                        .collect(Collectors.joining())
                + "Operation.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationQueryOperation that = (RelationQueryOperation) o;
        return Objects.equals(primaryName, that.primaryName) && Objects.equals(secondaryName, that.secondaryName) && Objects.equals(basePackage, that.basePackage) && Objects.equals(query, that.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryName, secondaryName, basePackage, query);
    }
}
