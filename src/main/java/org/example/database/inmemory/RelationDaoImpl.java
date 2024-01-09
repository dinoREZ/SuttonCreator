package org.example.database.inmemory;

import java.util.HashMap;
import java.util.Map;

public class RelationDaoImpl {
    String primaryName;
    String secondaryName;
    String basePackage;
    Map<String, String> queryAttributes;

    public RelationDaoImpl() {
        queryAttributes = new HashMap<>();
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

    public Map<String, String> getQueryAttributes() {
        return queryAttributes;
    }

    public void addQueryParameter(String type, String name) {
        queryAttributes.put(name, type);
    }
}
