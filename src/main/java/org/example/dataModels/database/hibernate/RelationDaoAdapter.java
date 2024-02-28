package org.example.dataModels.database.hibernate;

import org.example.Query;
import org.example.dataModels.DataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
