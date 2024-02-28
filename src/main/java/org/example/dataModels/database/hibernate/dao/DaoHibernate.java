package org.example.dataModels.database.hibernate.dao;

import org.example.Query;
import org.example.dataModels.DataModel;

import java.util.ArrayList;
import java.util.List;

public class DaoHibernate implements DataModel {

    private String name;
    private final List<Query> queries;
    private String basePackage;

    public DaoHibernate() {
        this.queries = new ArrayList<>();
    }

    public DaoHibernate(String name, List<Query> queries, String basePackage) {
        this.name = name;
        this.queries = queries;
        this.basePackage = basePackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String getOutputName() {
        return this.getName() + "DaoHibernate.java";
    }
}
