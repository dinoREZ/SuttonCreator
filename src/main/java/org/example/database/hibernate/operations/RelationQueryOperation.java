package org.example.database.hibernate.operations;

import org.example.Query;

import java.util.HashMap;
import java.util.Map;

public class RelationQueryOperation {

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
}
