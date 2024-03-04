package org.example.dataModels.database;

import org.example.Query;
import org.example.dataModels.DataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dao implements DataModel {

    private String name;
    private final List<Query> queries;
    private String basePackage;

    public Dao() {
        this.queries = new ArrayList<>();
    }

    public Dao(String name, List<Query> queries, String basePackage) {
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
        return this.getName() + "Dao.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dao dao = (Dao) o;
        return Objects.equals(name, dao.name) && Objects.equals(queries, dao.queries) && Objects.equals(basePackage, dao.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, queries, basePackage);
    }
}
