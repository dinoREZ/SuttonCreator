package org.example.dataModels.database.hibernate;

import org.example.Query;
import org.example.dataModels.DataModel;

import java.util.*;

public class RelationDaoAdapter implements DataModel {

    String primaryName;
    String secondaryName;
    String basePackage;
    Map<String, String> secondaryAttributes;
    private final List<Query> queries;


    public RelationDaoAdapter() {
        secondaryAttributes = new HashMap<>();
        this.queries = new ArrayList<>();
    }

    public RelationDaoAdapter(String primaryName, String secondaryName, String basePackage, Map<String, String> secondaryAttributes, List<Query> queries) {
        this.primaryName = primaryName;
        this.secondaryName = secondaryName;
        this.basePackage = basePackage;
        this.secondaryAttributes = secondaryAttributes;
        this.queries = queries;
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

    public Map<String, String> getSecondaryAttributes() {
        return secondaryAttributes;
    }

    public void addSecondaryAttribute(String type, String name) {
        secondaryAttributes.put(name, type);
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void addQuery(Query query) {
        queries.add(query);
    }

    @Override
    public String getOutputName() {
        return this.getPrimaryName() + this.getSecondaryName() + "DaoAdapter.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationDaoAdapter that = (RelationDaoAdapter) o;
        return Objects.equals(primaryName, that.primaryName) && Objects.equals(secondaryName, that.secondaryName) && Objects.equals(basePackage, that.basePackage) && Objects.equals(secondaryAttributes, that.secondaryAttributes) && Objects.equals(queries, that.queries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryName, secondaryName, basePackage, secondaryAttributes, queries);
    }
}
