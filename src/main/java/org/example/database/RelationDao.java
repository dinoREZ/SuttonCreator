package org.example.database;

import java.util.HashMap;
import java.util.Map;

public class RelationDao {

    String primaryName;
    String secondaryName;
    String basePackage;
    boolean hasQuery;
    Map<String, String> queryAttributes;

    public RelationDao() {
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

    public boolean isHasQuery() {
        return hasQuery;
    }

    public void setHasQuery(boolean hasQuery) {
        this.hasQuery = hasQuery;
    }

    public Map<String, String> getQueryAttributes() {
        return queryAttributes;
    }

    public void addQueryAttribute(String type, String name) {
        queryAttributes.put(name, type);
    }
}
