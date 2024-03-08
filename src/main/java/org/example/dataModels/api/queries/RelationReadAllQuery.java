package org.example.dataModels.api.queries;

import org.example.dataModels.DataModel;

import java.util.Objects;

public class RelationReadAllQuery implements DataModel {

    String primaryName;
    String secondaryName;
    String basePackage;

    public RelationReadAllQuery(String primaryName, String secondaryName, String basePackage) {
        this.primaryName = primaryName;
        this.secondaryName = secondaryName;
        this.basePackage = basePackage;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return primaryName + secondaryName + "ReadAllQuery.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationReadAllQuery that = (RelationReadAllQuery) o;
        return Objects.equals(primaryName, that.primaryName) && Objects.equals(secondaryName, that.secondaryName) && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryName, secondaryName, basePackage);
    }
}
