package org.example.database.hibernate.dao;

import java.util.HashMap;
import java.util.Map;

public class DaoHibernate {

    private String name;
    private boolean hasQuery;
    private final Map<String, String> queryAttributes;
    private String basePackage;

    public DaoHibernate() {
        this.queryAttributes = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasQuery() {
        return hasQuery;
    }

    public void setHasQuery(boolean hasQuery) {
        this.hasQuery = hasQuery;
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
