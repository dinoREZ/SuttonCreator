package org.example.database;

import org.example.Query;

import java.util.ArrayList;
import java.util.List;

public class RelationDao {

    String primaryName;
    String secondaryName;
    String basePackage;
    private final List<Query> queries;

    public RelationDao() {
        this.queries = new ArrayList<>();
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

    public List<Query> getQueries() {
        return queries;
    }

    public void addQuery(Query query) {
        queries.add(query);
    }
}
